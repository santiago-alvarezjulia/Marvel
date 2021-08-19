package com.saj.marvel.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericApiError(
    @Json(name = "code") val code: Int,
    @Json(name = "status") val status: String
)
