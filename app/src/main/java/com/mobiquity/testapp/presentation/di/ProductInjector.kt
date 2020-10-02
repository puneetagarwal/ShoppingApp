package com.mobiquity.testapp.presentation.di

import com.mobiquity.testapp.core.di.CoreInjector
import com.mobiquity.testapp.domain.di.ProductDomainInjector


object ProductInjector {

    lateinit var component: ProductComponent

    fun initialise() {
        component = DaggerProductComponent.builder()
            .coreComponent(CoreInjector.component)
            .productDomainComponent(ProductDomainInjector.component)
            .build()
    }
}