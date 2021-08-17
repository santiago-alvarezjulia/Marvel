package com.saj.marvel.network.mappers

import com.saj.marvel.Character
import com.saj.marvel.network.dtos.CharacterDTO
import javax.inject.Inject

class CharacterMapper @Inject constructor(): Mapper<CharacterDTO, Character> {
    override fun map(input: CharacterDTO): Character {
        return Character(input.id, input.name)
    }
}