package com.mobiquity.testapp.app

import android.app.Application

class ShoppingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initialiseDagger()
    }

    private fun initialiseDagger() {
        AppInjector.initialise(this)
    }
}