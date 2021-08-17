package com.saj.marvel.repositories

import com.saj.marvel.models.Character

interface CharactersRepositoryInt {
    suspend fun fetchMarvelCharacters(): List<Character>
}