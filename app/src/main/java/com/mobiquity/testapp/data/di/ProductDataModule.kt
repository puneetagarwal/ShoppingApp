package com.mobiquity.testapp.data.di

import com.mobiquity.testapp.BuildConfig
import com.mobiquity.testapp.data.respository.RestProductRepository
import com.mobiquity.testapp.data.service.ProductApiService
import com.mobiquity.testapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [ProductDataModule.BindModule::class])
class ProductDataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Module
    interface BindModule {

        @Binds
        fun bindRepository(repository: RestProductRepository): ProductRepository
    }
}