package com.saj.marvel.network

import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import retrofit2.http.GET

typealias GenericResponse<S> = NetworkResponse<S, GenericApiError>

interface MarvelWebService {
    @GET("characters")
    suspend fun fetchMarvelCharacters() : GenericResponse<DataWrapperDTO<CharacterDTO>>
}