package com.saj.marvel.di

import com.saj.marvel.Character
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.mappers.CharacterMapper
import com.saj.marvel.network.mappers.ListMapper
import com.saj.marvel.network.mappers.ListMapperImpl
import com.saj.marvel.network.mappers.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    abstract fun bindShowCharacterMapper(
        showCharacterMapper: CharacterMapper
    ): Mapper<CharacterDTO, Character>

    @Binds
    abstract fun bindShowCharacterListMapper(
        showCharactersMapper: ListMapperImpl<CharacterDTO, Character>
    ): ListMapper<CharacterDTO, Character>

}