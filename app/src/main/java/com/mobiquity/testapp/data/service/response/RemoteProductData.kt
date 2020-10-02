package com.mobiquity.testapp.data.service.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProductData(
    @Json(name = "id")
    val categoryId: String,
    @Json(name = "name")
    val categoryName: String,
    val products: List<RemoteProduct>
)