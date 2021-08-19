package com.saj.marvel.network.mappers

import com.saj.marvel.models.Character
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.ThumbnailDTO
import javax.inject.Inject

class CharacterMapper @Inject constructor(
    private val thumbnailMapper: Mapper<ThumbnailDTO, String>
): Mapper<CharacterDTO, Character> {
    override fun map(input: CharacterDTO): Character {
        return Character(input.id, input.name, input.description,
            thumbnailMapper.map(input.thumbnail))
    }
}