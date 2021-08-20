package com.saj.marvel.builders

import com.saj.marvel.models.Event

class EventBuilder {

    private var id = 1
    private var name = "Thanos Event"
    private var startDate = "2020-12-10 00:00:00"
    private var thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/6/40/5274137e3e2cd.jpg"
    private var comics = listOf(ComicBuilder().build())

    fun setId(newId: Int) : EventBuilder {
        this.id = newId
        return this
    }

    fun setName(newName: String) : EventBuilder {
        this.name = newName
        return this
    }

    fun setStartDate(newStartDate: String) : EventBuilder {
        this.startDate = newStartDate
        return this
    }

    fun setThumbnail(newThumbnail: String) : EventBuilder {
        this.thumbnail = newThumbnail
        return this
    }

    fun build() : Event {
        return Event(id, name, startDate, thumbnail, comics)
    }
}