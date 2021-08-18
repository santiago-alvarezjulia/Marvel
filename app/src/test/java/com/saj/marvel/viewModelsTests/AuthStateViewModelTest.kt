package com.saj.marvel.viewModelsTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.viewModels.AuthStateViewModel
import org.junit.Test

class AuthStateViewModelTest {

    @Test
    fun `when user has logged in, isUserLogged is true`() {
        val viewModel = AuthStateViewModel()
        assertThat(viewModel.isUserLogged()).isTrue()
    }
}