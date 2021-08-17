package com.saj.marvel.repositories

import com.saj.marvel.network.MarvelWebService
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val marvelWebService: MarvelWebService
) : CharactersRepositoryInt {
    override fun fetchMarvelCharacters(): List<String> {
        return marvelWebService.fetchMarvelCharacters()
    }
}