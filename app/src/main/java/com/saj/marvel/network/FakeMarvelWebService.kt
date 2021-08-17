package com.saj.marvel.network

import javax.inject.Inject

class FakeMarvelWebService @Inject constructor() : MarvelWebService {
    override suspend fun fetchMarvelCharacters(): List<String> {
        return emptyList()
    }
}