package com.saj.marvel.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.saj.marvel.repositories.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersPagingSource: CharactersPagingSource
) : ViewModel() {

    val charactersFlow = Pager(
        PagingConfig(
            pageSize = CharactersPagingSource.PAGE_SIZE
        )
    ) {
        charactersPagingSource
    }.flow
        .cachedIn(viewModelScope)
}