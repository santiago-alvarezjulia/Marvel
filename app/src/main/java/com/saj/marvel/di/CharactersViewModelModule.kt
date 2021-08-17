package com.saj.marvel.di

import com.saj.marvel.repositories.CharactersRepository
import com.saj.marvel.repositories.CharactersRepositoryInt
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharactersViewModelModule {

    @Binds
    abstract fun charactersRepository(
        charactersRepository: CharactersRepository
    ) : CharactersRepositoryInt
}