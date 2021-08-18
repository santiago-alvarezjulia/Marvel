package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.data.AuthStateStorage
import org.junit.Test

class AuthStateStorageTest {

    @Test
    fun `fetchAuthState return true when user logged in`() {
        val storage = AuthStateStorage()
        assertThat(storage.isUserLoggedIn()).isTrue()
    }
}