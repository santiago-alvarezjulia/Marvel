package com.saj.marvel.repositories

import com.saj.marvel.data.AuthStateStorageInt
import javax.inject.Inject

class AuthStateRepository @Inject constructor(
    private val authStateStorage: AuthStateStorageInt
) : AuthStateRepositoryInt {
    override fun fetchAuthState(): Boolean {
        return authStateStorage.isUserLoggedIn()
    }
}