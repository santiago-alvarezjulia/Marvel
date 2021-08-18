package com.saj.marvel.viewModelsTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.repositories.AuthStateRepositoryInt
import com.saj.marvel.viewModels.AuthStateViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class AuthStateViewModelTest {

    private val mockAuthStateRepo = mockk<AuthStateRepositoryInt>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when user has logged in, isUserLogged is true`() {
        stubRepoFetchLoggedIn(true)
        val viewModel = AuthStateViewModel(mockAuthStateRepo)
        assertThat(viewModel.isLoggedInLiveData.value?.peekContent()).isTrue()
    }

    @Test
    fun `when user has not logged in, isUserLogged is false`() {
        stubRepoFetchLoggedIn(false)
        val viewModel = AuthStateViewModel(mockAuthStateRepo)
        assertThat(viewModel.isLoggedInLiveData.value?.peekContent()).isFalse()
    }

    private fun stubRepoFetchLoggedIn(isLogged: Boolean) {
        coEvery { mockAuthStateRepo.fetchAuthState() } returns isLogged
    }
}