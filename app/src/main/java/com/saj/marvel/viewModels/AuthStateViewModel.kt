package com.saj.marvel.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saj.marvel.repositories.AuthStateRepositoryInt
import com.saj.marvel.viewModels.singleEvent.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthStateViewModel @Inject constructor(
    private val authStateRepository: AuthStateRepositoryInt
) : ViewModel() {

    private val _isLoggedInLiveData = MutableLiveData<Event<Boolean>>()
    val isLoggedInLiveData : LiveData<Event<Boolean>>
        get() = _isLoggedInLiveData

    init {
        isUserLogged()
    }

    private fun isUserLogged() {
        val isLoggedIn = authStateRepository.fetchAuthState()
        _isLoggedInLiveData.postValue(Event(isLoggedIn))
    }
}