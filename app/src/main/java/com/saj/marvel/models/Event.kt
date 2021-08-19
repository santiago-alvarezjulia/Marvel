package com.saj.marvel.models

data class Event(val id: Int, val title: String, val startDate: String,
                 val thumbnail: String) {
    fun isTheSame(otherEvent: Event): Boolean {
        return this.id == otherEvent.id
    }

    fun isContentTheSame(otherEvent: Event): Boolean {
        return this.title == otherEvent.title &&
                this.startDate == otherEvent.startDate &&
                this.thumbnail == otherEvent.thumbnail
    }
}
