package com.saj.marvel.network

import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import com.saj.marvel.network.dtos.EventDTO
import retrofit2.http.GET
import retrofit2.http.Query

typealias GenericResponse<S> = NetworkResponse<S, GenericApiError>

interface MarvelWebService {
    @GET("characters")
    suspend fun fetchMarvelCharacters() : GenericResponse<DataWrapperDTO<CharacterDTO>>

    @GET("characters")
    suspend fun fetchPagedMarvelCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : GenericResponse<DataWrapperDTO<CharacterDTO>>

    @GET("events")
    suspend fun fetchMarvelEvents(
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String
    ) : GenericResponse<DataWrapperDTO<EventDTO>>
}