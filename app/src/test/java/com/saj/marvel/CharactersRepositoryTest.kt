package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.repositories.CharactersRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class CharactersRepositoryTest {

    private val mockMarvelWebService = mockk<MarvelWebService>()

    @Test
    fun `fetchMarvelCharacters return empty list when no characters`() {
        val repo = CharactersRepository(mockMarvelWebService)
        stubWebServiceFetchCharacters(emptyList())
        assertThat(repo.fetchMarvelCharacters()).isEmpty()
    }

    @Test
    fun `fetchMarvelCharacters return list of available characters`() {
        val repo = CharactersRepository(mockMarvelWebService)
        val characters = listOf("Thanos", "Thor")
        stubWebServiceFetchCharacters(characters)
        assertThat(repo.fetchMarvelCharacters()).hasSize(characters.size)
    }

    private fun stubWebServiceFetchCharacters(characters: List<String>) {
        every { mockMarvelWebService.fetchMarvelCharacters() } returns characters
    }
}