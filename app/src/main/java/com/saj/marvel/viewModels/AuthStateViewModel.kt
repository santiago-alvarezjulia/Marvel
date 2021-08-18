package com.saj.marvel.viewModels

import androidx.lifecycle.*
import com.saj.marvel.di.IoDispatcher
import com.saj.marvel.repositories.AuthStateRepositoryInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthStateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val authStateRepository: AuthStateRepositoryInt,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    companion object {
        private const val SAVED_STATE_IS_LOGGED_KEY = "SAVED_STATE_IS_LOGGED_KEY"
    }

    private val _isLoggedInLiveData = MutableLiveData<Boolean>()
    val isLoggedInLiveData : LiveData<Boolean>
        get() = _isLoggedInLiveData

    init {
        val savedStateLoggedIn = savedStateHandle.get<Boolean>(SAVED_STATE_IS_LOGGED_KEY)
        savedStateLoggedIn?.let {
            _isLoggedInLiveData.postValue(it)
        } ?: run {
            isUserLogged()
        }
    }

    private fun isUserLogged() {
        viewModelScope.launch(dispatcher) {
            authStateRepository.fetchAuthState().collect {
                _isLoggedInLiveData.postValue(it)
                savedStateHandle.set(SAVED_STATE_IS_LOGGED_KEY, it)
            }
        }
    }
}