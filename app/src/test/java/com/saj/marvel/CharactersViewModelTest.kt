package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.repositories.CharactersRepositoryInt
import com.saj.marvel.viewModels.CharactersViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class CharactersViewModelTest {

    private val mockCharactersRepo = mockk<CharactersRepositoryInt>()

    @Test
    fun `getMarvelCharacters return empty list when no characters`() {
        val viewModel = CharactersViewModel(mockCharactersRepo)
        stubRepoFetchCharacters(emptyList())
        assertThat(viewModel.getMarvelCharacters()).isEmpty()
    }

    @Test
    fun `getMarvelCharacters return list of available characters`() {
        val viewModel = CharactersViewModel(mockCharactersRepo)
        val characters = listOf("Thanos", "Thor")
        stubRepoFetchCharacters(characters)
        assertThat(viewModel.getMarvelCharacters()).hasSize(characters.size)
    }

    private fun stubRepoFetchCharacters(characters: List<String>) {
        every { mockCharactersRepo.fetchMarvelCharacters() } returns characters
    }
}