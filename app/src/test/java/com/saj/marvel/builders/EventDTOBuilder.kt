package com.saj.marvel.builders

import com.saj.marvel.network.dtos.ComicListDTO
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.dtos.ThumbnailDTO

class EventDTOBuilder {

    private var id = 1
    private var name = "Thanos"
    private var startDate : String? = "2020-12-10 00:00:00"
    private var thumbnail = ThumbnailDTOBuilder().build()
    private var comics = ComicListDTO(listOf(
        ComicListDTO.ComicSummaryDTO(
            "http://gateway.marvel.com/v1/public/comics/12744",
            "Alpha Flight (1983) #79")
    ))

    fun setId(newId: Int) : EventDTOBuilder {
        this.id = newId
        return this
    }

    fun setName(newName: String) : EventDTOBuilder {
        this.name = newName
        return this
    }

    fun setStartDate(newStartDate: String?) : EventDTOBuilder {
        this.startDate = newStartDate
        return this
    }

    fun setThumbnail(newThumbnail: ThumbnailDTO) : EventDTOBuilder {
        this.thumbnail = newThumbnail
        return this
    }

    fun setNewComics(newComics: ComicListDTO) : EventDTOBuilder {
        this.comics = newComics
        return this
    }

    fun build() : EventDTO {
        return EventDTO(id, name, startDate, thumbnail, comics)
    }
}