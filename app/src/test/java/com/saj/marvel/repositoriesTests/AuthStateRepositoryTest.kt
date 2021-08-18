package com.saj.marvel.repositoriesTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.repositories.AuthStateRepository
import org.junit.Test

class AuthStateRepositoryTest {

    @Test
    fun `fetchAuthState return true when user logged in`() {
        val repo = AuthStateRepository()
        assertThat(repo.fetchAuthState()).isTrue()
    }
}