package com.mobiquity.testapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobiquity.testapp.core.ext.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.testapp.R
import com.mobiquity.testapp.core.ext.gone
import com.mobiquity.testapp.core.ext.visible
import com.mobiquity.testapp.core.presentation.Resource
import com.mobiquity.testapp.presentation.di.ProductInjector
import com.mobiquity.testapp.presentation.list.ProductListViewModel.Companion.CONNECTION_ERROR
import com.mobiquity.testapp.presentation.list.adapter.ProductRecyclerViewAdapter
import com.mobiquity.testapp.presentation.model.ItemProductViewData
import com.mobiquity.testapp.presentation.model.ProductViewData
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment(), ProductRecyclerViewAdapter.OnItemClickListener {

    private val viewModel by viewModel(ProductInjector.component.getProductListViewModelFactory())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getProducts()
        setOnClickListeners()
        initialiseViewModelObservers()
    }

    private fun getProducts() {
        viewModel.getProducts()
    }

    private fun setOnClickListeners() {
        btnTryAgain.setOnClickListener { onTryAgainClicked() }
    }

    private fun onTryAgainClicked() {
        viewModel.tryAgain()
    }

    private fun initialiseViewModelObservers() {
        viewModel.productListLiveData.observe(viewLifecycleOwner, ::onGetProductsStateUpdate)
    }

    private fun onGetProductsStateUpdate(resource: Resource<List<ProductViewData>>) {
        when (resource.state) {
            Resource.State.Loading -> onGetProductsStateLoading()
            Resource.State.Success -> onGetProductsStateSuccess(resource.data)
            Resource.State.Error -> onGetProductsStateError(resource.message)
        }
    }

    private fun onGetProductsStateLoading() {
        showLoading()
    }

    private fun showLoading() {
        groupErrorViews.gone()
        recyclerViewProductList.gone()
        progressLoading.visible()
    }

    private fun onGetProductsStateSuccess(dataList: List<ProductViewData>?) {
        dataList?.let { setUpRecyclerView(it) }
        showProducts()
    }

    private fun setUpRecyclerView(repositories: List<ProductViewData>) {
        recyclerViewProductList.run {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ProductRecyclerViewAdapter(this@ProductListFragment, repositories)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
    }

    override fun onItemClicked(item: ItemProductViewData) {
        findNavController().navigate(ProductListFragmentDirections.actionToProductDetailFragment())
    }

    private fun showProducts() {
        recyclerViewProductList.visible()
        progressLoading.gone()
        groupErrorViews.gone()
    }

    private fun onGetProductsStateError(message: String?) {
        when (message) {
            CONNECTION_ERROR -> showNetworkError()
            else -> showUnknownError()
        }
    }

    private fun showNetworkError() {
        recyclerViewProductList.gone()
        progressLoading.gone()
        groupErrorViews.visible()
        textError.text = getString(R.string.error_message_connection)
    }

    private fun showUnknownError() {
        recyclerViewProductList.gone()
        progressLoading.gone()
        groupErrorViews.visible()
        textError.text = getString(R.string.error_message_unknown)
    }
}