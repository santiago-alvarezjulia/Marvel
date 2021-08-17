package com.saj.marvel.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataWrapperDTO<out T>(@Json(name = "data") val data: DataContainerDTO<T>) {
    @JsonClass(generateAdapter = true)
    data class DataContainerDTO<out T>(@Json(name = "results") val results: List<T>)
}
