package com.saj.marvel.repositoriesTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterBuilder
import com.saj.marvel.builders.CharacterDTOBuilder
import com.saj.marvel.models.Character
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import com.saj.marvel.network.mappers.ListMapper
import com.saj.marvel.repositories.CharactersRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersRepositoryTest {

    private val mockMarvelWebService = mockk<MarvelWebService>()
    private val listMapper = mockk<ListMapper<CharacterDTO, Character>>()

    @Test
    fun `fetchMarvelCharacters return empty list when no characters`() = runBlockingTest {
        val repo = CharactersRepository(mockMarvelWebService, listMapper)
        stubWebServiceFetchCharacters(emptyList())
        stubCharactersListMapper(emptyList())
        assertThat(repo.fetchMarvelCharacters()).isEmpty()
    }

    @Test
    fun `fetchMarvelCharacters return list of available characters`() = runBlockingTest {
        val repo = CharactersRepository(mockMarvelWebService, listMapper)
        val character = CharacterDTOBuilder().build()
        stubCharactersListMapper(listOf(CharacterBuilder().build()))
        stubWebServiceFetchCharacters(listOf(character))
        assertThat(repo.fetchMarvelCharacters()[0].id).isEqualTo(character.id)
    }

    private fun stubCharactersListMapper(characters: List<Character>) {
        every { listMapper.map(any()) } returns characters
    }

    private fun stubWebServiceFetchCharacters(characters: List<CharacterDTO>) {
        val data = DataWrapperDTO(DataWrapperDTO.DataContainerDTO(characters))
        coEvery { mockMarvelWebService.fetchMarvelCharacters() } returns NetworkResponse.Success(data)
    }
}