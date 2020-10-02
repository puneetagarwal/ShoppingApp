package com.mobiquity.testapp.data.respository

import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.data.respository.factory.ProductDataFactory
import com.mobiquity.testapp.data.service.ProductApiService
import com.mobiquity.testapp.domain.model.ProductData
import com.mobiquity.testapp.domain.repository.ProductRepository
import io.reactivex.Single
import javax.inject.Inject

class RestProductRepository @Inject constructor(
    private val apiService: ProductApiService,
    private val productDataFactory: ProductDataFactory,
    private val schedulerProvider: SchedulerProvider
) : ProductRepository {

    override fun getProducts(): Single<ProductData> {
        return apiService.getProducts()
            .map { productDataFactory.create(it) }
            .subscribeOn(schedulerProvider.io)
    }
}