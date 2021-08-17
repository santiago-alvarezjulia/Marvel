package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.viewModels.ListCharactersViewModel
import org.junit.Test

class ListCharactersViewModelTest {

    @Test
    fun `getMarvelCharacters return empty list when no characters`() {
        val viewModel = ListCharactersViewModel()
        assertThat(viewModel.getMarvelCharacters()).isEmpty()
    }
}