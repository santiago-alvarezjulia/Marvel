package com.saj.marvel.repositories

import com.saj.marvel.data.AuthStateStorageInt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthStateRepository @Inject constructor(
    private val authStateStorage: AuthStateStorageInt
){
    fun fetchAuthState(): Flow<Boolean> {
        return authStateStorage.isUserLoggedIn()
    }
}