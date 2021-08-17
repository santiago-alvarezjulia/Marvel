package com.saj.marvel.network

import javax.inject.Inject

class FakeMarvelWebService @Inject constructor() : MarvelWebService {
    override fun fetchMarvelCharacters(): List<String> {
        return emptyList()
    }
}