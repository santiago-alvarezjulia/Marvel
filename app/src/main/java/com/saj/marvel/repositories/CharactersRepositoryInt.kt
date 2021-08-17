package com.saj.marvel.repositories

interface CharactersRepositoryInt {
    fun fetchMarvelCharacters(): List<String>
}