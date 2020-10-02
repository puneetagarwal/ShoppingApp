package com.mobiquity.testapp.data.di

import com.mobiquity.testapp.core.di.CoreComponent
import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.domain.repository.ProductRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Provider

@ProductDataScope
@Component(modules = [ProductDataModule::class], dependencies = [CoreComponent::class])
interface ProductDataComponent {

    fun getProductRepository(): Provider<ProductRepository>

    fun getSchedulerProvider(): SchedulerProvider

    fun getRetrofit(): Retrofit

    @Component.Builder
    interface Builder {

        fun build(): ProductDataComponent

        fun coreComponent(coreComponent: CoreComponent): Builder
    }
}