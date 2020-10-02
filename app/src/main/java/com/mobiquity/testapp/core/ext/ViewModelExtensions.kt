package com.mobiquity.testapp.core.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.mobiquity.testapp.core.presentation.ViewModelFactory

inline fun <reified VM : ViewModel> Fragment.viewModel(viewModelFactory: ViewModelFactory<VM>? = null): Lazy<VM> {
    return lazy {
        ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
    }
}