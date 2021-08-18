package com.saj.marvel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterBuilder
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.models.Character
import com.saj.marvel.repositories.CharactersRepositoryInt
import com.saj.marvel.utils.MainCoroutineRule
import com.saj.marvel.viewModels.CharactersViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    private val mockCharactersRepo = mockk<CharactersRepositoryInt>()
    private val mockSavedStateHandle = mockk<SavedStateHandle>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun mockEspressoCountingIdlingResource() {
        mockkObject(EspressoCountingIdlingResource)
        every { EspressoCountingIdlingResource.processStarts() } returns Unit
        every { EspressoCountingIdlingResource.processEnds() } returns Unit
    }

    @Test
    fun `getMarvelCharacters return empty list when no characters`() {
        stubRepoFetchCharacters(emptyList())
        stubSavedStateHandleGet(emptyList())
        val viewModel = CharactersViewModel(mockSavedStateHandle, mockCharactersRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.charactersLiveData.value).isEmpty()
    }

    @Test
    fun `getMarvelCharacters return list of available characters`() {
        val characters = listOf(CharacterBuilder().build())
        stubRepoFetchCharacters(characters)
        stubSavedStateHandleGet(emptyList())
        val viewModel = CharactersViewModel(mockSavedStateHandle, mockCharactersRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.charactersLiveData.value).hasSize(characters.size)
    }

    @Test
    fun `saved state handle characters return instead of repo getCharacters`() {
        val characters = listOf(CharacterBuilder().build())
        stubRepoFetchCharacters(emptyList())
        stubSavedStateHandleGet(characters)
        val viewModel = CharactersViewModel(mockSavedStateHandle, mockCharactersRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.charactersLiveData.value).hasSize(characters.size)
    }

    private fun stubRepoFetchCharacters(characters: List<Character>) {
        coEvery { mockCharactersRepo.fetchMarvelCharacters() } returns characters
    }

    private fun stubSavedStateHandleGet(characters: List<Character>) {
        every { mockSavedStateHandle.get<List<Character>>(any()) } returns characters
    }
}