package com.saj.marvel.repositoriesTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.data.AuthStateStorageInt
import com.saj.marvel.repositories.AuthStateRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class AuthStateRepositoryTest {

    private val mockAuthStateStorage = mockk<AuthStateStorageInt>()

    @Test
    fun `fetchAuthState return true when user logged in`() = runBlockingTest {
        stubIsUserLoggedIn(true)
        val repo = AuthStateRepository(mockAuthStateStorage)
        assertThat(repo.fetchAuthState()).isTrue()
    }

    @Test
    fun `fetchAuthState return false when user not logged in`() = runBlockingTest {
        stubIsUserLoggedIn(false)
        val repo = AuthStateRepository(mockAuthStateStorage)
        assertThat(repo.fetchAuthState()).isFalse()
    }

    private fun stubIsUserLoggedIn(isLoggedIn: Boolean) {
        every { mockAuthStateStorage.isUserLoggedIn() } returns isLoggedIn
    }
}