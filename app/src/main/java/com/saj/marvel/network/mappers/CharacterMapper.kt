package com.saj.marvel.network.mappers

import com.saj.marvel.models.Character
import com.saj.marvel.network.dtos.CharacterDTO
import javax.inject.Inject

class CharacterMapper @Inject constructor(): Mapper<CharacterDTO, Character> {
    override fun map(input: CharacterDTO): Character {
        val thumbnail = input.thumbnail.path.plus('.').plus(input.thumbnail.extension)
        return Character(input.id, input.name, input.description, thumbnail)
    }
}