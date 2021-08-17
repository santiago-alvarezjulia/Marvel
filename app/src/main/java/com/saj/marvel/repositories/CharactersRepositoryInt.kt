package com.saj.marvel.repositories

import com.saj.marvel.Character

interface CharactersRepositoryInt {
    suspend fun fetchMarvelCharacters(): List<Character>
}