package com.saj.marvel.di

import com.saj.marvel.data.AuthStateStorage
import com.saj.marvel.data.AuthStateStorageInt
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthStateRepositoryModule {

    @Binds
    abstract fun authStateStorage(
        authStateStorage: AuthStateStorage
    ) : AuthStateStorageInt
}