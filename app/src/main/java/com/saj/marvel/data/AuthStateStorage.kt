package com.saj.marvel.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthStateStorage @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    companion object {
        const val DATA_STORE_NAME = "auth_state"
        private const val IS_LOGGED_IN_KEY = "is_logged_in"
    }

    private val isLoggedInKey = booleanPreferencesKey(IS_LOGGED_IN_KEY)

    fun isUserLoggedIn() : Flow<Boolean> =  appContext.authStateDataStore.data
            .map { preferences ->
                preferences[isLoggedInKey] ?: false
            }

    suspend fun userLoggedIn() {
        appContext.authStateDataStore.edit { settings ->
            settings[isLoggedInKey] = true
        }
    }
}