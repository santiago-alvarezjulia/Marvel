package com.saj.marvel.repositories

import com.saj.marvel.models.Event
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.NetworkResponse

interface EventsRepositoryInt {
    suspend fun fetchMarvelEvents(): NetworkResponse<List<Event>, GenericApiError>
}