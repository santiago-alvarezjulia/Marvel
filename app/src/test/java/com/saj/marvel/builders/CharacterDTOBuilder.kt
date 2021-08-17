package com.saj.marvel.builders

import com.saj.marvel.network.dtos.CharacterDTO

class CharacterDTOBuilder {

    private var id = 1
    private var name = "Thanos"

    fun setId(newId: Int) : CharacterDTOBuilder {
        this.id = newId
        return this
    }

    fun setName(newName: String) : CharacterDTOBuilder {
        this.name = newName
        return this
    }

    fun build() : CharacterDTO {
        return CharacterDTO(id, name)
    }
}