package com.mobiquity.testapp.presentation.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiquity.testapp.core.ext.setError
import com.mobiquity.testapp.core.ext.setLoading
import com.mobiquity.testapp.core.ext.setSuccess
import com.mobiquity.testapp.core.presentation.Resource
import com.mobiquity.testapp.domain.model.ProductData
import com.mobiquity.testapp.domain.usecase.GetProductsUseCase
import com.mobiquity.testapp.presentation.model.ProductViewData
import com.mobiquity.testapp.presentation.model.factory.ProductViewDataFactory
import javax.inject.Inject

open class ProductListViewModel @Inject constructor(
    private val useCase: GetProductsUseCase,
    private val viewDataFactory: ProductViewDataFactory
) : ViewModel() {

    companion object {
        const val CONNECTION_ERROR = "connection_error"
    }

    private val _productListLiveData = MutableLiveData<Resource<List<ProductViewData>>>()
    val productListLiveData: LiveData<Resource<List<ProductViewData>>>
        get() = _productListLiveData

    fun getProducts() {
        if (_productListLiveData.value != null) return
        retrieveProducts()
    }

    private fun retrieveProducts() {
        _productListLiveData.setLoading()
        useCase.execute(::onGetProductsResult)
    }

    @VisibleForTesting
    fun onGetProductsResult(result: GetProductsUseCase.Result) {
        when (result) {
            is GetProductsUseCase.Result.Success -> onGetProductsSuccess(result.data)
            GetProductsUseCase.Result.ErrorConnection -> onGetProductsConnectionError()
            GetProductsUseCase.Result.ErrorUnknown -> onGetProductsUnknownError()
        }
    }

    private fun onGetProductsSuccess(data: ProductData) {
        val productListViewData = viewDataFactory.create(data.products)
        _productListLiveData.setSuccess(productListViewData)
    }

    private fun onGetProductsConnectionError() {
        _productListLiveData.setError(CONNECTION_ERROR)
    }

    private fun onGetProductsUnknownError() {
        _productListLiveData.setError()
    }

    fun tryAgain() {
        retrieveProducts()
    }

    override fun onCleared() {
        super.onCleared()
        useCase.cancel()
    }
}