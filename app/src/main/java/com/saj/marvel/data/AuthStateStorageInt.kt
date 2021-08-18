package com.saj.marvel.data

import kotlinx.coroutines.flow.Flow

interface AuthStateStorageInt {
    fun isUserLoggedIn() : Flow<Boolean>
    suspend fun userLoggedIn()
}