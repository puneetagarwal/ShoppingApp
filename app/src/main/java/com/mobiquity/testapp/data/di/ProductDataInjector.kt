package com.mobiquity.testapp.data.di

import com.mobiquity.testapp.core.di.CoreInjector
import com.mobiquity.testapp.domain.di.ProductDomainInjector

object ProductDataInjector {

    lateinit var component: ProductDataComponent

    fun initialise() {
        component = DaggerProductDataComponent.builder()
            .coreComponent(CoreInjector.component)
            .build()

        with(component) {
            ProductDomainInjector.initialise(getProductRepository(), getSchedulerProvider())
        }
    }
}