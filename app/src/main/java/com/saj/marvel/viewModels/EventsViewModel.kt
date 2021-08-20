package com.saj.marvel.viewModels

import androidx.lifecycle.*
import com.saj.marvel.R
import com.saj.marvel.di.IoDispatcher
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.repositories.EventsRepositoryInt
import com.saj.marvel.ui.models.ListedEvent
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

    private val _listedEventsLiveData = MutableLiveData<List<ListedEvent>>()
    val listedEventsLiveData : LiveData<List<ListedEvent>>
        get() = _listedEventsLiveData

    private val _loadEventsErrorLiveData = MutableLiveData<com.saj.marvel.viewModels.singleEvent.Event<Int>>()
    val loadEventsErrorLiveData : LiveData<com.saj.marvel.viewModels.singleEvent.Event<Int>>
        get() = _loadEventsErrorLiveData

    init {
        val savedStateEvents = savedStateHandle.get<List<ListedEvent>>(SAVED_STATE_EVENTS_KEY)
        savedStateEvents?.let {
            if (it.isEmpty()) {
                getMarvelEvents()
            } else {
                _listedEventsLiveData.postValue(it)
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
                    val listedEvents = response.body.map {
                        ListedEvent(it)
                    }
                    _listedEventsLiveData.postValue(listedEvents)
                    savedStateHandle.set(SAVED_STATE_EVENTS_KEY, listedEvents)
                }
                else -> {
                    _loadEventsErrorLiveData.postValue(
                        com.saj.marvel.viewModels.singleEvent.Event(R.string.error_loading_events)
                    )
                }
            }
            EspressoCountingIdlingResource.processEnds()
        }
    }
}
