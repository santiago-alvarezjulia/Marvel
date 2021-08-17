package com.saj.marvel.builders

import com.saj.marvel.models.Character

class CharacterBuilder {

    private val id = 1
    private val name = "Thanos"
    private val description = "The Mad Titan Thanos, a melancholy, brooding individual"
    private val thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/6/40/5274137e3e2cd.jpg"

    fun build() : Character {
        return Character(id, name, description, thumbnail)
    }
}