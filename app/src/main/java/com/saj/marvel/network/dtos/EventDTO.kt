package com.saj.marvel.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "start") val startDate: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailDTO
)
