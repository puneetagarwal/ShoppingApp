package com.mobiquity.testapp.core.domain

import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T> {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun execute(onResult: (result: T) -> Unit)

    open fun cancel() {
        compositeDisposable.clear()
    }
}