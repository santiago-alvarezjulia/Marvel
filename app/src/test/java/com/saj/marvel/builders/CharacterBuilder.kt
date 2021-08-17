package com.saj.marvel.builders

import com.saj.marvel.models.Character

class CharacterBuilder {

    private var id = 1
    private var name = "Thanos"
    private var description = "The Mad Titan Thanos, a melancholy, brooding individual"
    private var thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/6/40/5274137e3e2cd.jpg"

    fun setId(newId: Int) : CharacterBuilder {
        this.id = newId
        return this
    }

    fun setName(newName: String) : CharacterBuilder {
        this.name = newName
        return this
    }

    fun setDescription(newDescription: String) : CharacterBuilder {
        this.description = newDescription
        return this
    }

    fun setThumbnail(newThumbnail: String) : CharacterBuilder {
        this.thumbnail = newThumbnail
        return this
    }

    fun build() : Character {
        return Character(id, name, description, thumbnail)
    }
}