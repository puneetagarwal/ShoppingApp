package com.mobiquity.testapp.domain.usecase

import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.core.rx.TestSchedulerProvider
import com.mobiquity.testapp.domain.model.ProductData
import com.mobiquity.testapp.domain.repository.ProductRepository
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.net.ConnectException

@RunWith(MockitoJUnitRunner::class)
class GetProductsUseCaseTest {

    @Mock
    private lateinit var repository: ProductRepository

    @Mock
    private lateinit var productData: ProductData

    private lateinit var useCase: GetProductsUseCase

    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()

    private var result: GetProductsUseCase.Result? = null

    @Before
    fun setUp() {
        useCase = GetProductsUseCase(repository, schedulerProvider)
    }

    @Test
    fun `gets product data successfully`() {
        val expected = GetProductsUseCase.Result.Success(productData)
        given(repository.getProducts()).willReturn(Single.just(productData))

        useCase.execute { result = it }

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should handle connection error`() {
        val expected = GetProductsUseCase.Result.ErrorConnection
        given(repository.getProducts()).willReturn(Single.error(ConnectException()))

        useCase.execute { result = it }

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should handle unknown error`() {
        val expected = GetProductsUseCase.Result.ErrorUnknown
        given(repository.getProducts()).willReturn(Single.error(RuntimeException()))

        useCase.execute { result = it }

        assertThat(result).isEqualTo(expected)
    }
}