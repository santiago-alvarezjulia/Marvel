package com.saj.marvel.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ComicListDTO(@Json(name = "items") val comicSummaries: List<ComicSummaryDTO>) {

    @JsonClass(generateAdapter = true)
    data class ComicSummaryDTO(@Json(name = "resourceURI") val resourceUri: String,
                               @Json(name = "name") val name: String)
}