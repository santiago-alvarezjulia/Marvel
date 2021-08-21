package com.saj.marvel.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataWrapperDTO<out T>(@Json(name = "data") val data: DataContainerDTO<T>) {

    @JsonClass(generateAdapter = true)
    data class DataContainerDTO<out T>(
        @Json(name = "offset") val offset: Int,
        @Json(name = "count") val count: Int,
        @Json(name = "total") val total: Int,
        @Json(name = "results") val results: List<T>
    )
}
