package com.saj.marvel.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saj.marvel.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSharedViewModel @Inject constructor() : ViewModel() {

    private val _characterLiveData =  MutableLiveData<Character>()
    val characterLiveData: LiveData<Character>
        get() = _characterLiveData

    fun setCharacter(character: Character) {
        _characterLiveData.postValue(character)
    }
}