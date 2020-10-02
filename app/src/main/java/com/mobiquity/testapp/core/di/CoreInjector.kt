package com.mobiquity.testapp.core.di

import android.app.Application

object CoreInjector {

    lateinit var component: CoreComponent

    fun initialise(application: Application) {
        component = DaggerCoreComponent.builder()
            .application(application)
            .build()
    }
}