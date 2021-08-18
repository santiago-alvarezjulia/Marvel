package com.saj.marvel.viewModels

import androidx.lifecycle.*
import com.saj.marvel.di.IoDispatcher
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.models.Character
import com.saj.marvel.repositories.CharactersRepositoryInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val charactersRepository: CharactersRepositoryInt,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    companion object {
        private const val SAVED_STATE_CHARACTERS_KEY = "SAVED_STATE_CHARACTERS_KEY"
    }

    private val _charactersLiveData = MutableLiveData<List<Character>>()
    val charactersLiveData : LiveData<List<Character>>
        get() = _charactersLiveData

    init {
        val savedStateCharacters = savedStateHandle.get<List<Character>>(SAVED_STATE_CHARACTERS_KEY)
        savedStateCharacters?.let {
            if (it.isEmpty()) {
                getMarvelCharacters()
            } else {
                _charactersLiveData.postValue(it)
            }
        } ?: run {
            getMarvelCharacters()
        }
    }

    private fun getMarvelCharacters() {
        EspressoCountingIdlingResource.processStarts()
        viewModelScope.launch(dispatcher) {
            val characters = charactersRepository.fetchMarvelCharacters()
            _charactersLiveData.postValue(characters)
            savedStateHandle.set(SAVED_STATE_CHARACTERS_KEY, characters)
            EspressoCountingIdlingResource.processEnds()
        }
    }
}