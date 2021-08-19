package com.saj.marvel.repositoriesTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventBuilder
import com.saj.marvel.builders.EventDTOBuilder
import com.saj.marvel.models.Event
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.DataWrapperDTO
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.mappers.ListMapper
import com.saj.marvel.repositories.EventsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class EventsRepositoryTest {

    private val mockMarvelWebService = mockk<MarvelWebService>()
    private val listMapper = mockk<ListMapper<EventDTO, Event>>()

    @Test
    fun `fetchMarvelEvents return empty list when no events`() = runBlockingTest {
        val repo = EventsRepository(mockMarvelWebService, listMapper)
        stubWebServiceFetchCharacters(emptyList())
        stubEventsListMapper(emptyList())
        val response = repo.fetchMarvelEvents()
        assertThat((response as NetworkResponse.Success).body).isEmpty()
    }

    @Test
    fun `fetchMarvelEvents return list of available events`() = runBlockingTest {
        val repo = EventsRepository(mockMarvelWebService, listMapper)
        val eventDTO = EventDTOBuilder().build()
        stubEventsListMapper(listOf(EventBuilder().build()))
        stubWebServiceFetchCharacters(listOf(eventDTO))
        val response = repo.fetchMarvelEvents()
        assertThat((response as NetworkResponse.Success).body[0].id).isEqualTo(eventDTO.id)
    }

    @Test
    fun `when network response is ApiError, fetch events return the error`() = runBlockingTest {
        val error = NetworkResponse.ApiError(GenericApiError(500, "error"))
        stubWebServiceApiError(error)
        val repo = EventsRepository(mockMarvelWebService, listMapper)
        val response = repo.fetchMarvelEvents()
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun `when network response is NetworkError, fetch events return the error`() = runBlockingTest {
        val error = NetworkResponse.NetworkError(IOException())
        stubWebServiceNetworkError(error)
        val repo = EventsRepository(mockMarvelWebService, listMapper)
        val response = repo.fetchMarvelEvents()
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun `when network response is OtherError, fetch events return the error`() = runBlockingTest {
        val error = NetworkResponse.OtherError(null)
        stubWebServiceOtherError(error)
        val repo = EventsRepository(mockMarvelWebService, listMapper)
        val response = repo.fetchMarvelEvents()
        assertThat(response).isEqualTo(error)
    }

    private fun stubEventsListMapper(events: List<Event>) {
        every { listMapper.map(any()) } returns events
    }

    private fun stubWebServiceFetchCharacters(events: List<EventDTO>) {
        val data = DataWrapperDTO(DataWrapperDTO.DataContainerDTO(events))
        coEvery { mockMarvelWebService.fetchMarvelEvents(any(), any()) } returns NetworkResponse.Success(data)
    }

    private fun stubWebServiceApiError(error: NetworkResponse.ApiError<GenericApiError>) {
        coEvery { mockMarvelWebService.fetchMarvelEvents(any(), any()) } returns error
    }

    private fun stubWebServiceNetworkError(error: NetworkResponse.NetworkError) {
        coEvery { mockMarvelWebService.fetchMarvelEvents(any(), any()) } returns error
    }

    private fun stubWebServiceOtherError(error: NetworkResponse.OtherError) {
        coEvery { mockMarvelWebService.fetchMarvelEvents(any(), any()) } returns error
    }
}