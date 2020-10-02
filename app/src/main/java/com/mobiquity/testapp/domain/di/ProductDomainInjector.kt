package com.mobiquity.testapp.domain.di

import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.domain.repository.ProductRepository
import javax.inject.Provider

object ProductDomainInjector {

    lateinit var component: ProductDomainComponent

    fun initialise(repository: Provider<ProductRepository>, schedulerProvider: SchedulerProvider) {
        component = DaggerProductDomainComponent.builder()
            .productDomainModule(ProductDomainModule(repository, schedulerProvider))
            .build()
    }
}