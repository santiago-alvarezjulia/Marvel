package com.saj.marvel.models

data class Event(val id: Int, val name: String, val startDate: String,
                 val thumbnail: String) {
    fun isTheSame(otherEvent: Event): Boolean {
        return this.id == otherEvent.id
    }

    fun isContentTheSame(otherEvent: Event): Boolean {
        return this.name == otherEvent.name &&
                this.startDate == otherEvent.startDate &&
                this.thumbnail == otherEvent.thumbnail
    }
}
