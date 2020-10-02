package com.mobiquity.testapp.domain.di

import com.mobiquity.testapp.domain.usecase.GetProductsUseCase
import dagger.Component

@Component(modules = [ProductDomainModule::class])
interface ProductDomainComponent {

    fun getGetProductsUseCase(): GetProductsUseCase

    @Component.Builder
    interface Builder {

        fun build(): ProductDomainComponent

        fun productDomainModule(productDomainModule: ProductDomainModule): Builder
    }
}
