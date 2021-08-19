package com.saj.marvel.viewModels

import androidx.lifecycle.*
import com.saj.marvel.R
import com.saj.marvel.di.IoDispatcher
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.models.Character
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.repositories.CharactersRepositoryInt
import com.saj.marvel.viewModels.singleEvent.Event
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

    private val _loadCharactersErrorLiveData = MutableLiveData<Event<Int>>()
    val loadCharactersErrorLiveData : LiveData<Event<Int>>
        get() = _loadCharactersErrorLiveData

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
            when (val response = charactersRepository.fetchMarvelCharacters()) {
                is NetworkResponse.Success -> {
                    val characters = response.body
                    _charactersLiveData.postValue(characters)
                    savedStateHandle.set(SAVED_STATE_CHARACTERS_KEY, characters)
                }
                else -> {
                    _loadCharactersErrorLiveData.postValue(
                        Event(R.string.error_loading_characters)
                    )
                }
            }
            EspressoCountingIdlingResource.processEnds()
        }
    }
}