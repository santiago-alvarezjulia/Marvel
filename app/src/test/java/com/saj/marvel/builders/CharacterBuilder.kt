package com.saj.marvel.builders

import com.saj.marvel.models.Character

class CharacterBuilder {

    private val id = 1
    private val name = "Thanos"

    fun build() : Character {
        return Character(id, name)
    }
}