package com.saj.marvel.di

import com.saj.marvel.models.Character
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.ThumbnailDTO
import com.saj.marvel.network.mappers.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    abstract fun bindThumbnailMapper(
        thumbnailMapper: ThumbnailMapper
    ): Mapper<ThumbnailDTO, String>

    @Binds
    abstract fun bindCharacterMapper(
        characterMapper: CharacterMapper
    ): Mapper<CharacterDTO, Character>

    @Binds
    abstract fun bindCharacterListMapper(
        charactersMapper: ListMapperImpl<CharacterDTO, Character>
    ): ListMapper<CharacterDTO, Character>

}