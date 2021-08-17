package com.saj.marvel.repositories

interface CharactersRepositoryInt {
    suspend fun fetchMarvelCharacters(): List<String>
}