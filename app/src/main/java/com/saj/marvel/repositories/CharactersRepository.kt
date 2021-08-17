package com.saj.marvel.repositories

import com.saj.marvel.Character
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.mappers.ListMapper
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val marvelWebService: MarvelWebService,
    private val charactersMapper: ListMapper<CharacterDTO, Character>
) : CharactersRepositoryInt {
    override suspend fun fetchMarvelCharacters(): List<Character> {
        return charactersMapper.map(
            marvelWebService.fetchMarvelCharacters().data.results
        )
    }
}