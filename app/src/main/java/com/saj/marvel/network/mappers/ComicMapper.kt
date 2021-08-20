package com.saj.marvel.network.mappers

import com.saj.marvel.models.Comic
import com.saj.marvel.network.dtos.ComicListDTO
import javax.inject.Inject

class ComicMapper @Inject constructor(): Mapper<ComicListDTO.ComicSummaryDTO, Comic> {
    override fun map(input: ComicListDTO.ComicSummaryDTO): Comic {
        val comicId = input.resourceUri.substringAfterLast('/').toInt()
        return Comic(comicId, input.name)
    }
}