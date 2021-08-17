package com.saj.marvel.models

data class Character(val id: Int, val name: String, val description: String,
    val thumbnail: String) {

    fun isTheSame(otherCharacter: Character): Boolean {
        return this.id == otherCharacter.id
    }

    fun isContentTheSame(otherCharacter: Character): Boolean {
        return this.name == otherCharacter.name &&
                this.description == otherCharacter.description &&
                this.thumbnail == otherCharacter.thumbnail
    }
}
