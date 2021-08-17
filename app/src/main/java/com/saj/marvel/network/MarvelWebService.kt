package com.saj.marvel.network

import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import retrofit2.http.GET

interface MarvelWebService {
    @GET("characters")
    suspend fun fetchMarvelCharacters() : DataWrapperDTO<CharacterDTO>
}