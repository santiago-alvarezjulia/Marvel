package com.saj.marvel.repositories

import com.saj.marvel.models.Event
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.mappers.ListMapper
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val webService: MarvelWebService,
    private val eventsMapper: ListMapper<EventDTO, Event>
) {
    companion object {
        private const val LIMIT = 25
        private const val ORDER_BY = "startDate"
    }

    suspend fun fetchMarvelEvents(): NetworkResponse<List<Event>, GenericApiError> {
        return when(val response = webService.fetchMarvelEvents(LIMIT, ORDER_BY)) {
            is NetworkResponse.Success -> {
                NetworkResponse.Success(
                    eventsMapper.map(response.body.data.results)
                )
            }
            is NetworkResponse.ApiError -> NetworkResponse.ApiError(response.body)
            is NetworkResponse.NetworkError -> NetworkResponse.NetworkError(response.error)
            is NetworkResponse.OtherError -> NetworkResponse.OtherError(response.error)
        }
    }
}