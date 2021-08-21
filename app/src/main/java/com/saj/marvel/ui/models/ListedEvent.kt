package com.saj.marvel.ui.models

import com.saj.marvel.models.Comic
import com.saj.marvel.models.Event

data class ListedEvent(private val event: Event, val isExpanded: Boolean = false) {

    fun isTheSame(otherListedEvent: ListedEvent): Boolean {
        return this.getId() == otherListedEvent.getId()
    }

    fun isContentTheSame(otherListedEvent: ListedEvent): Boolean {
        return this.getTitle() == otherListedEvent.getTitle() &&
                this.getStartDate() == otherListedEvent.getStartDate() &&
                this.getThumbnail() == otherListedEvent.getThumbnail()
    }

    fun getId(): Int {
        return event.id
    }

    fun getTitle(): String {
        return event.title
    }

    fun getStartDate(): String {
        return event.startDate
    }

    fun getThumbnail(): String {
        return event.thumbnail
    }

    fun getComics(): List<Comic> {
        return event.comics
    }
}
