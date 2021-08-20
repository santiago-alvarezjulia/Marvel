package com.saj.marvel.repositoriesTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterDTOBuilder
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import com.saj.marvel.network.mappers.CharacterMapper
import com.saj.marvel.network.mappers.ListMapperImpl
import com.saj.marvel.network.mappers.ThumbnailMapper
import com.saj.marvel.repositories.CharactersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class CharactersRepositoryTest {

    private val mockMarvelWebService = mockk<MarvelWebService>()
    private val realListMapper = ListMapperImpl(
        CharacterMapper(
            ThumbnailMapper()
        )
    )

    @Test
    fun `fetchMarvelCharacters return empty list when no characters`() = runBlockingTest {
        val repo = CharactersRepository(mockMarvelWebService, realListMapper)
        stubWebServiceFetchCharacters(emptyList())
        val response = repo.fetchMarvelCharacters()
        assertThat((response as NetworkResponse.Success).body).isEmpty()
    }

    @Test
    fun `fetchMarvelCharacters return list of available characters`() = runBlockingTest {
        val repo = CharactersRepository(mockMarvelWebService, realListMapper)
        val character = CharacterDTOBuilder().build()
        stubWebServiceFetchCharacters(listOf(character))
        val response = repo.fetchMarvelCharacters()
        assertThat((response as NetworkResponse.Success).body[0].id).isEqualTo(character.id)
    }

    @Test
    fun `when network response is ApiError, fetch characters return the error`() = runBlockingTest {
        val error = NetworkResponse.ApiError(GenericApiError(500, "error"))
        stubWebServiceApiError(error)
        val repo = CharactersRepository(mockMarvelWebService, realListMapper)
        val response = repo.fetchMarvelCharacters()
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun `when network response is NetworkError, fetch characters return the error`() = runBlockingTest {
        val error = NetworkResponse.NetworkError(IOException())
        stubWebServiceNetworkError(error)
        val repo = CharactersRepository(mockMarvelWebService, realListMapper)
        val response = repo.fetchMarvelCharacters()
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun `when network response is OtherError, fetch characters return the error`() = runBlockingTest {
        val error = NetworkResponse.OtherError(null)
        stubWebServiceOtherError(error)
        val repo = CharactersRepository(mockMarvelWebService, realListMapper)
        val response = repo.fetchMarvelCharacters()
        assertThat(response).isEqualTo(error)
    }

    private fun stubWebServiceFetchCharacters(characters: List<CharacterDTO>) {
        val data = DataWrapperDTO(DataWrapperDTO.DataContainerDTO(characters))
        coEvery { mockMarvelWebService.fetchMarvelCharacters() } returns NetworkResponse.Success(data)
    }

    private fun stubWebServiceApiError(error: NetworkResponse.ApiError<GenericApiError>) {
        coEvery { mockMarvelWebService.fetchMarvelCharacters() } returns error
    }

    private fun stubWebServiceNetworkError(error: NetworkResponse.NetworkError) {
        coEvery { mockMarvelWebService.fetchMarvelCharacters() } returns error
    }

    private fun stubWebServiceOtherError(error: NetworkResponse.OtherError) {
        coEvery { mockMarvelWebService.fetchMarvelCharacters() } returns error
    }
}