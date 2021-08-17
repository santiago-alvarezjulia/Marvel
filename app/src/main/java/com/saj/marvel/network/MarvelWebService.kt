package com.saj.marvel.network

interface MarvelWebService {
    suspend fun fetchMarvelCharacters() : List<String>
}