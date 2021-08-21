package com.saj.marvel.viewModelsTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterBuilder
import com.saj.marvel.viewModels.CharacterSharedViewModel
import org.junit.Rule
import org.junit.Test

class CharacterSharedViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `setCharacter update characterLiveData value`() {
        val character = CharacterBuilder().build()
        val viewModel = CharacterSharedViewModel()
        viewModel.setCharacter(character)
        assertThat(viewModel.characterLiveData.value).isEqualTo(character)
    }
}