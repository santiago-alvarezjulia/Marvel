package com.saj.marvel.network

interface MarvelWebService {
    fun fetchMarvelCharacters() : List<String>
}