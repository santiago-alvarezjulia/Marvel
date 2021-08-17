package com.saj.marvel.viewModels

import com.saj.marvel.repositories.CharactersRepositoryInt

class ListCharactersViewModel(
    private val charactersRepository: CharactersRepositoryInt
) {

    fun getMarvelCharacters() : List<String> {
        return charactersRepository.fetchMarvelCharacters()
    }
}