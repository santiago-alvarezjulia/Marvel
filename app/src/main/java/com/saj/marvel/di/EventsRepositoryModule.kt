package com.saj.marvel.di

import com.saj.marvel.models.Event
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.mappers.EventMapper
import com.saj.marvel.network.mappers.ListMapper
import com.saj.marvel.network.mappers.ListMapperImpl
import com.saj.marvel.network.mappers.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class EventsRepositoryModule {

    @Binds
    abstract fun bindEventMapper(
        eventMapper: EventMapper
    ): Mapper<EventDTO, Event>

    @Binds
    abstract fun bindEventListMapper(
        listMapper: ListMapperImpl<EventDTO, Event>
    ): ListMapper<EventDTO, Event>

}