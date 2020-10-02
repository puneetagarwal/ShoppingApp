package com.mobiquity.testapp.data.service

import com.mobiquity.testapp.data.service.response.RemoteProductData
import io.reactivex.Single
import retrofit2.http.GET

interface ProductApiService {

    @GET(".")
    fun getProducts(): Single<List<RemoteProductData>>
}