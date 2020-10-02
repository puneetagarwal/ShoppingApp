package com.mobiquity.testapp.domain.usecase

import com.mobiquity.testapp.core.domain.UseCase
import com.mobiquity.testapp.core.ext.disposeWith
import com.mobiquity.testapp.core.ext.isConnectionError
import com.mobiquity.testapp.core.rx.SchedulerProvider
import com.mobiquity.testapp.domain.model.ProductData
import com.mobiquity.testapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val schedulerProvider: SchedulerProvider
) : UseCase<GetProductsUseCase.Result>() {

    override fun execute(onResult: (result: Result) -> Unit) {
        repository.getProducts()
            .map(::mapToResult)
            .onErrorReturn(::getErrorResult)
            .subscribeOn(schedulerProvider.newThread)
            .observeOn(schedulerProvider.mainThread)
            .subscribe(onResult)
            .disposeWith(compositeDisposable)
    }

    private fun mapToResult(data: ProductData): Result {
        return Result.Success(data)
    }

    private fun getErrorResult(throwable: Throwable): Result {
        return when {
            throwable.isConnectionError() -> Result.ErrorConnection
            else -> Result.ErrorUnknown
        }
    }

    sealed class Result {
        data class Success(val data: ProductData) : Result()
        object ErrorConnection : Result()
        object ErrorUnknown : Result()
    }
}