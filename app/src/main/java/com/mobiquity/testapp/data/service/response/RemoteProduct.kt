package com.mobiquity.testapp.data.service.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProduct(
    val id: String,
    val name: String,
    @Json(name = "url")
    val imageUrl: String?,
    @Json(name = "salePrice")
    val price: RemoteProductPrice
)