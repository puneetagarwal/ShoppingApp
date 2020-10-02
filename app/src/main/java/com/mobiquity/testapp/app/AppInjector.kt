package com.mobiquity.testapp.app

import android.app.Application
import com.mobiquity.testapp.core.di.CoreInjector
import com.mobiquity.testapp.data.di.ProductDataInjector
import com.mobiquity.testapp.presentation.di.ProductInjector

object AppInjector {

    fun initialise(application: Application) {
        initialiseCoreDependencies(application)
        initialiseProductDependencies()
    }

    private fun initialiseCoreDependencies(application: Application) {
        CoreInjector.initialise(application)
    }

    private fun initialiseProductDependencies() {
        ProductDataInjector.initialise()
        ProductInjector.initialise()
    }
}