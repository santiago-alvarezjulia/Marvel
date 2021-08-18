package com.saj.marvel.viewModelsTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.repositories.AuthStateRepositoryInt
import com.saj.marvel.utils.MainCoroutineRule
import com.saj.marvel.utils.runBlockingTest
import com.saj.marvel.viewModels.AuthStateViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AuthStateViewModelTest {

    private val mockAuthStateRepo = mockk<AuthStateRepositoryInt>()
    private val mockSavedStateHandle = mockk<SavedStateHandle>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when user has logged in, isUserLogged is true`() = coroutineRule.runBlockingTest {
        stubRepoFetchLoggedIn(true)
        stubSavedStateHandleGet(null)
        val viewModel = AuthStateViewModel(mockSavedStateHandle, mockAuthStateRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.isLoggedInLiveData.value).isTrue()
    }

    @Test
    fun `when user has not logged in, isUserLogged is false`() = coroutineRule.runBlockingTest {
        stubRepoFetchLoggedIn(false)
        stubSavedStateHandleGet(null)
        val viewModel = AuthStateViewModel(mockSavedStateHandle, mockAuthStateRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.isLoggedInLiveData.value).isFalse()
    }

    @Test
    fun `when saved state is logged in, isUserLogged is true`() = coroutineRule.runBlockingTest {
        stubRepoFetchLoggedIn(false)
        stubSavedStateHandleGet(true)
        val viewModel = AuthStateViewModel(mockSavedStateHandle, mockAuthStateRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.isLoggedInLiveData.value).isTrue()
    }

    private fun stubRepoFetchLoggedIn(isLogged: Boolean) {
        coEvery { mockAuthStateRepo.fetchAuthState() } returns flow {
            emit(isLogged)
        }
    }

    private fun stubSavedStateHandleGet(isLogged: Boolean?) {
        every { mockSavedStateHandle.get<Boolean>(any()) } returns isLogged
    }
}