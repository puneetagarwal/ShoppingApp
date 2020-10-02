package com.mobiquity.testapp.core.di

import android.app.Application
import android.content.Context
import com.mobiquity.testapp.core.presentation.ImageLoader
import com.mobiquity.testapp.core.rx.SchedulerProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, ImageModule::class])
interface CoreComponent {

    fun getSchedulerProvider(): SchedulerProvider

    fun getContext(): Context

    fun getImageLoader(): ImageLoader

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}