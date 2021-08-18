package com.saj.marvel.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.authStateDataStore: DataStore<Preferences> by preferencesDataStore(
    name = AuthStateStorage.DATA_STORE_NAME
)
