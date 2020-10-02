package com.mobiquity.testapp.domain.di

import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ProductDomainModule(
    private val repository: Provider<ProductRepository>,
    private val schedulerProvider: SchedulerProvider
) {

    @Provides
    fun provideProductRepository(): ProductRepository = repository.get()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = schedulerProvider
}