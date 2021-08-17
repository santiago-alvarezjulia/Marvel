package com.saj.marvel.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: ThumbnailDTO
) {
    @JsonClass(generateAdapter = true)
    data class ThumbnailDTO(
        @Json(name = "path") val path: String,
        @Json(name = "extension") val extension: String
    )
}
