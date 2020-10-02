package com.mobiquity.testapp.core.presentation

data class Resource<out T>(
    val state: State,
    val data: T? = null,
    val message: String? = null
) {

    sealed class State {
        object Loading : State()
        object Success : State()
        object Error : State()
    }
}