package com.mobiquity.testapp.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiquity.testapp.core.presentation.Resource
import com.mobiquity.testapp.domain.model.FoodProduct
import com.mobiquity.testapp.domain.model.ProductData
import com.mobiquity.testapp.domain.usecase.GetProductsUseCase
import com.mobiquity.testapp.presentation.list.ProductListViewModel.Companion.CONNECTION_ERROR
import com.mobiquity.testapp.presentation.model.ItemProductViewData
import com.mobiquity.testapp.presentation.model.ProductViewData
import com.mobiquity.testapp.presentation.model.factory.ProductViewDataFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest {

    @Mock
    private lateinit var useCase: GetProductsUseCase

    @Mock
    private lateinit var factory: ProductViewDataFactory

    private lateinit var viewModel: ProductListViewModel

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TestProductListViewModel(useCase, factory)
    }

    @Test
    fun `get products should set loading state and execute use case only once`() {
        val captor = argumentCaptor<(GetProductsUseCase.Result) -> Unit>()
        val expected = Resource<List<ProductViewData>>(Resource.State.Loading)

        viewModel.getProducts()
        viewModel.getProducts()

        then(useCase).should(times(1)).execute(captor.capture())
        assertThat(viewModel.productListLiveData.value).isEqualTo(expected)
    }

    @Test
    fun `try again should set loading state and execute use case again`() {
        val captor = argumentCaptor<(GetProductsUseCase.Result) -> Unit>()
        val expected = Resource<List<ProductViewData>>(Resource.State.Loading)

        viewModel.tryAgain()

        then(useCase).should().execute(captor.capture())
        assertThat(viewModel.productListLiveData.value).isEqualTo(expected)
    }

    @Test
    fun `try again should execute use case again`() {
        val captor = argumentCaptor<(GetProductsUseCase.Result) -> Unit>()

        viewModel.tryAgain()
        viewModel.tryAgain()

        then(useCase).apply {
            should(times(2)).execute(captor.capture())
            shouldHaveNoMoreInteractions()
        }
    }

    @Test
    fun `should handle get products success result`() {
        val productViewData = ItemProductViewData("name", "imageUrl", "salePrice")
        val productViewDataList = listOf(productViewData)
        val product = FoodProduct("itemId", "categoryId", "name", "imageUrl", "salePrice")
        val productList = listOf(product)
        val data = ProductData(productList)
        val result = GetProductsUseCase.Result.Success(data)
        given(factory.create(productList)).willReturn(productViewDataList)
        val expected = Resource(Resource.State.Success, productViewDataList)

        viewModel.onGetProductsResult(result)

        assertThat(viewModel.productListLiveData.value).isEqualTo(expected)
    }

    @Test
    fun `should handle get products connection error`() {
        val result = GetProductsUseCase.Result.ErrorConnection
        val expected = Resource<List<ProductViewData>>(state = Resource.State.Error, message = CONNECTION_ERROR)

        viewModel.onGetProductsResult(result)

        assertThat(viewModel.productListLiveData.value).isEqualTo(expected)
    }

    @Test
    fun `should handle get products unknown error`() {
        val result = GetProductsUseCase.Result.ErrorUnknown
        val expected = Resource<List<ProductViewData>>(Resource.State.Error)

        viewModel.onGetProductsResult(result)

        assertThat(viewModel.productListLiveData.value).isEqualTo(expected)
    }

    @Test
    fun `on cleared should cancel the use cases`() {
        (viewModel as TestProductListViewModel).invokeOnCleared()

        then(useCase).should().cancel()
    }

    private class TestProductListViewModel(
        useCase: GetProductsUseCase,
        factory: ProductViewDataFactory
    ) : ProductListViewModel(useCase, factory) {

        fun invokeOnCleared() {
            onCleared()
        }
    }
}