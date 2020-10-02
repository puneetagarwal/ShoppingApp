package com.mobiquity.testapp.core.di

import android.app.Application
import android.content.Context
import com.mobiquity.testapp.core.rx.DefaultSchedulerProvider
import com.mobiquity.testapp.core.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return DefaultSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}