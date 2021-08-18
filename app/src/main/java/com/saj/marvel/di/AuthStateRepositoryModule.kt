package com.saj.marvel.di

import com.saj.marvel.data.AuthStateStorageInt
import com.saj.marvel.data.FirebaseAuthStateStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthStateRepositoryModule {

    @Binds
    abstract fun authStateStorage(
        firebaseAuthStateStorage: FirebaseAuthStateStorage
    ) : AuthStateStorageInt
}