package com.saj.marvel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterBuilder
import com.saj.marvel.repositories.CharactersRepositoryInt
import com.saj.marvel.utils.MainCoroutineRule
import com.saj.marvel.utils.runBlockingTest
import com.saj.marvel.viewModels.CharactersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    private val mockCharactersRepo = mockk<CharactersRepositoryInt>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `getMarvelCharacters return empty list when no characters`() = coroutineRule.runBlockingTest {
        val viewModel = CharactersViewModel(mockCharactersRepo, coroutineRule.testDispatcher)
        stubRepoFetchCharacters(emptyList())
        viewModel.getMarvelCharacters()
        assertThat(viewModel.charactersLiveData.value).isEmpty()
    }

    @Test
    fun `getMarvelCharacters return list of available characters`() {
        val viewModel = CharactersViewModel(mockCharactersRepo, coroutineRule.testDispatcher)
        val characters = listOf(CharacterBuilder().build())
        stubRepoFetchCharacters(characters)
        viewModel.getMarvelCharacters()
        assertThat(viewModel.charactersLiveData.value).hasSize(characters.size)
    }

    private fun stubRepoFetchCharacters(characters: List<Character>) {
        coEvery { mockCharactersRepo.fetchMarvelCharacters() } returns characters
    }
}