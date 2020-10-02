package com.mobiquity.testapp.core.ext

import androidx.lifecycle.MutableLiveData
import com.mobiquity.testapp.core.presentation.Resource

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource(Resource.State.Success, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(Resource.State.Loading, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(Resource.State.Error, value?.data, message))
