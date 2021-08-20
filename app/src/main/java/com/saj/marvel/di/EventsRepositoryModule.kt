package com.saj.marvel.di

import com.saj.marvel.models.Comic
import com.saj.marvel.models.Event
import com.saj.marvel.network.dtos.ComicListDTO
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.mappers.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class EventsRepositoryModule {

    @Binds
    abstract fun bindComicMapper(
        comicMapper: ComicMapper
    ): Mapper<ComicListDTO.ComicSummaryDTO, Comic>

    @Binds
    abstract fun bindComicListMapper(
        listMapper: ListMapperImpl<ComicListDTO.ComicSummaryDTO, Comic>
    ): ListMapper<ComicListDTO.ComicSummaryDTO, Comic>

    @Binds
    abstract fun bindEventMapper(
        eventMapper: EventMapper
    ): Mapper<EventDTO, Event>

    @Binds
    abstract fun bindEventListMapper(
        listMapper: ListMapperImpl<EventDTO, Event>
    ): ListMapper<EventDTO, Event>

}