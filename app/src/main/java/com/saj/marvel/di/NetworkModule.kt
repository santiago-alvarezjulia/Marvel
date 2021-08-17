package com.saj.marvel.di

import com.saj.marvel.network.FakeMarvelWebService
import com.saj.marvel.network.MarvelWebService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun marvelWebService(
        marvelWebService: FakeMarvelWebService
    ) : MarvelWebService
}