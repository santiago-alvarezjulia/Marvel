package com.saj.marvel.builders

import com.saj.marvel.network.dtos.CharacterDTO

class CharacterDTOBuilder {

    private var id = 1
    private var name = "Thanos"
    private var description = "The Mad Titan Thanos, a melancholy, brooding individual"
    private var thumbnail = ThumbnailDTOBuilder().build()

    fun setId(newId: Int) : CharacterDTOBuilder {
        this.id = newId
        return this
    }

    fun setName(newName: String) : CharacterDTOBuilder {
        this.name = newName
        return this
    }

    fun setDescription(newDescription: String) : CharacterDTOBuilder {
        this.description = newDescription
        return this
    }

    fun setThumbnail(newThumbnail: CharacterDTO.ThumbnailDTO) : CharacterDTOBuilder {
        this.thumbnail = newThumbnail
        return this
    }

    fun build() : CharacterDTO {
        return CharacterDTO(id, name, description, thumbnail)
    }
}