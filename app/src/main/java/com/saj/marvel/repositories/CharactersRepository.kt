package com.saj.marvel.repositories

import com.saj.marvel.models.Character
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.mappers.ListMapper
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val marvelWebService: MarvelWebService,
    private val charactersMapper: ListMapper<CharacterDTO, Character>
) : CharactersRepositoryInt {
    override suspend fun fetchMarvelCharacters(): NetworkResponse<List<Character>, GenericApiError> {
        return when(val response = marvelWebService.fetchMarvelCharacters()) {
            is NetworkResponse.Success -> {
                NetworkResponse.Success(charactersMapper.map(
                    response.body.data.results
                ))
            }
            is NetworkResponse.ApiError -> NetworkResponse.ApiError(response.body)
            is NetworkResponse.NetworkError -> NetworkResponse.NetworkError(response.error)
            is NetworkResponse.OtherError -> NetworkResponse.OtherError(response.error)
        }
    }
}