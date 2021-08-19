package com.saj.marvel.di

import com.saj.marvel.repositories.EventsRepository
import com.saj.marvel.repositories.EventsRepositoryInt
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class EventsViewModelModule {

    @Binds
    abstract fun eventsRepository(
        eventsRepository: EventsRepository
    ) : EventsRepositoryInt
}