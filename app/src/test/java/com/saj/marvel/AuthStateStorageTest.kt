package com.saj.marvel

import androidx.datastore.preferences.core.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.data.AuthStateStorage
import com.saj.marvel.data.authStateDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AuthStateStorageTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @After
    fun clearAuthStateStorageBetweenTests() {
        runBlocking {
            context.authStateDataStore.edit {
                it.clear()
            }
        }
    }

    @Test
    fun `fetchAuthState return true when user logged in`() = runBlocking {
        val storage = AuthStateStorage(context)
        storage.userLoggedIn()
        assertThat(storage.isUserLoggedIn().first()).isTrue()
    }

    @Test
    fun `fetchAuthState return false when user not logged in`() = runBlocking {
        val storage = AuthStateStorage(context)
        assertThat(storage.isUserLoggedIn().first()).isFalse()
    }
}