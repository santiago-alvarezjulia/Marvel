package com.saj.marvel.viewModels

import androidx.lifecycle.*
import com.saj.marvel.R
import com.saj.marvel.di.IoDispatcher
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.models.Event
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.repositories.EventsRepositoryInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val eventsRepository: EventsRepositoryInt,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    companion object {
        private const val SAVED_STATE_EVENTS_KEY = "SAVED_STATE_EVENTS_KEY"
    }

    private val _eventsLiveData = MutableLiveData<List<Event>>()
    val eventsLiveData : LiveData<List<Event>>
        get() = _eventsLiveData

    private val _loadEventsErrorLiveData = MutableLiveData<com.saj.marvel.viewModels.singleEvent.Event<Int>>()
    val loadEventsErrorLiveData : LiveData<com.saj.marvel.viewModels.singleEvent.Event<Int>>
        get() = _loadEventsErrorLiveData

    init {
        val savedStateEvents = savedStateHandle.get<List<Event>>(SAVED_STATE_EVENTS_KEY)
        savedStateEvents?.let {
            if (it.isEmpty()) {
                getMarvelEvents()
            } else {
                _eventsLiveData.postValue(it)
            }
        } ?: run {
            getMarvelEvents()
        }
    }

    private fun getMarvelEvents() {
        EspressoCountingIdlingResource.processStarts()
        viewModelScope.launch(dispatcher) {
            when (val response = eventsRepository.fetchMarvelEvents()) {
                is NetworkResponse.Success -> {
                    val events = response.body
                    _eventsLiveData.postValue(events)
                    savedStateHandle.set(SAVED_STATE_EVENTS_KEY, events)
                }
                else -> {
                    _loadEventsErrorLiveData.postValue(
                        com.saj.marvel.viewModels.singleEvent.Event(R.string.error_loading_characters)
                    )
                }
            }
            EspressoCountingIdlingResource.processEnds()
        }
    }
}
