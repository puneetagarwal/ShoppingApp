package com.mobiquity.testapp.presentation.di

import com.mobiquity.testapp.core.di.CoreComponent
import com.mobiquity.testapp.core.presentation.ViewModelFactory
import com.mobiquity.testapp.domain.di.ProductDomainComponent
import com.mobiquity.testapp.presentation.list.ProductListViewModel
import dagger.Component

@ProductScope
@Component(dependencies = [ProductDomainComponent::class, CoreComponent::class])
interface ProductComponent {

    fun getProductListViewModelFactory(): ViewModelFactory<ProductListViewModel>

    @Component.Builder
    interface Builder {

        fun build(): ProductComponent

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun productDomainComponent(productDomainComponent: ProductDomainComponent): Builder
    }
}