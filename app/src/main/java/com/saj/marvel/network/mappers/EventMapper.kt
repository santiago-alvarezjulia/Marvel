package com.saj.marvel.network.mappers

import com.saj.marvel.models.Event
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.dtos.ThumbnailDTO
import javax.inject.Inject

class EventMapper @Inject constructor(
    private val thumbnailMapper: Mapper<ThumbnailDTO, String>
) : Mapper<EventDTO, Event> {
    override fun map(input: EventDTO): Event {
        return Event(input.id, input.name, input.startDate, thumbnailMapper.map(input.thumbnail))
    }
}