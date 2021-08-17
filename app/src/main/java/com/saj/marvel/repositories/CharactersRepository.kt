package com.saj.marvel.repositories

import com.saj.marvel.network.MarvelWebService

class CharactersRepository(
    private val marvelWebService: MarvelWebService
) : CharactersRepositoryInt {
    override fun fetchMarvelCharacters(): List<String> {
        return marvelWebService.fetchMarvelCharacters()
    }
}