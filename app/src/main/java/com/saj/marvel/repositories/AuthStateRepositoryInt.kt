package com.saj.marvel.repositories

import kotlinx.coroutines.flow.Flow

interface AuthStateRepositoryInt {
    fun fetchAuthState(): Flow<Boolean>
}