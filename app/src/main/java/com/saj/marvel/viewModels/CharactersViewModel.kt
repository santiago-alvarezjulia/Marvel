package com.saj.marvel.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saj.marvel.di.IoDispatcher
import com.saj.marvel.models.Character
import com.saj.marvel.repositories.CharactersRepositoryInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepositoryInt,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _charactersLiveData = MutableLiveData<List<Character>>()
    val charactersLiveData : LiveData<List<Character>>
        get() = _charactersLiveData

    init {
        getMarvelCharacters()
    }

    fun getMarvelCharacters() {
        viewModelScope.launch(dispatcher) {
            _charactersLiveData.postValue(charactersRepository.fetchMarvelCharacters())
        }
    }
}