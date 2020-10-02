package com.mobiquity.testapp.data.service.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProductPrice(val amount: String, val currency: String)