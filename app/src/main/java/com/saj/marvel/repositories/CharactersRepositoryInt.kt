package com.saj.marvel.repositories

import com.saj.marvel.models.Character
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.NetworkResponse

interface CharactersRepositoryInt {
    suspend fun fetchMarvelCharacters(): NetworkResponse<List<Character>, GenericApiError>
}