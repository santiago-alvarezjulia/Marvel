package com.saj.marvel.viewModels

import androidx.lifecycle.ViewModel
import com.saj.marvel.repositories.CharactersRepositoryInt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepositoryInt
) : ViewModel() {

    fun getMarvelCharacters() : List<String> {
        return charactersRepository.fetchMarvelCharacters()
    }
}