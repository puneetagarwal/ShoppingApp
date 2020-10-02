package com.mobiquity.testapp.data.respository

import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.core.rx.TestSchedulerProvider
import com.mobiquity.testapp.data.respository.factory.ProductDataFactory
import com.mobiquity.testapp.data.service.ProductApiService
import com.mobiquity.testapp.data.service.response.RemoteProductData
import com.mobiquity.testapp.domain.model.ProductData
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestProductRepositoryTest {

    private lateinit var repository: RestProductRepository

    @Mock
    private lateinit var service: ProductApiService

    @Mock
    private lateinit var productList: List<RemoteProductData>

    @Mock
    private lateinit var factory: ProductDataFactory

    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()

    @Before
    fun setUp() {
        repository = RestProductRepository(service, factory, schedulerProvider)
    }

    @Test
    fun `gets products successfully`() {
        val productData = ProductData(emptyList())
        given(service.getProducts()).willReturn(Single.just(productList))
        given(factory.create(productList)).willReturn(productData)

        val result = repository.getProducts().blockingGet()

        assertThat(result).isEqualTo(productData)
    }

    @Test
    fun `emits exception when get product fails`() {
        val exception = RuntimeException("unknown error")
        given(service.getProducts()).willReturn(Single.error(exception))

        val subscriber = repository.getProducts().test()

        subscriber.assertError(exception)
    }
}