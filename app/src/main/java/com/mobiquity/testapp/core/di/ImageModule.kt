package com.mobiquity.testapp.core.di

import com.mobiquity.testapp.core.presentation.GlideImageLoader
import com.mobiquity.testapp.core.presentation.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader()
    }
}