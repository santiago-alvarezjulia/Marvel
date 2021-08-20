package com.saj.marvel.network.mappers

import com.saj.marvel.models.Comic
import com.saj.marvel.models.Event
import com.saj.marvel.network.dtos.ComicListDTO
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.dtos.ThumbnailDTO
import javax.inject.Inject

class EventMapper @Inject constructor(
    private val thumbnailMapper: Mapper<ThumbnailDTO, String>,
    private val comicsMapper: ListMapper<ComicListDTO.ComicSummaryDTO, Comic>
) : Mapper<EventDTO, Event> {
    override fun map(input: EventDTO): Event {
        return Event(input.id, input.title, input.startDate ?: "",
            thumbnailMapper.map(input.thumbnail), comicsMapper.map(input.comics.comicSummaries))
    }
}