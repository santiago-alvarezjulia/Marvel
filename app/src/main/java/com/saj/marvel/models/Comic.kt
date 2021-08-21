package com.saj.marvel.models

data class Comic(val id: Int, val name: String) {

    fun isTheSame(otherComic: Comic): Boolean {
        return this.id == otherComic.id
    }

    fun isContentTheSame(otherComic: Comic): Boolean {
        return this.name == otherComic.name
    }
}
