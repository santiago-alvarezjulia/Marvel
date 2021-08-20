package com.saj.marvel.repositoriesTests

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventDTOBuilder
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.ComicListDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import com.saj.marvel.network.dtos.EventDTO
import com.saj.marvel.network.mappers.ComicMapper
import com.saj.marvel.network.mappers.EventMapper
import com.saj.marvel.network.mappers.ListMapperImpl
import com.saj.marvel.network.mappers.ThumbnailMapper
import com.saj.marvel.repositories.EventsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class EventsRepositoryTest {

    private val mockMarvelWebService = mockk<MarvelWebService>()
    private val realListMapper = ListMapperImpl(
        EventMapper(
            ThumbnailMapper(),
            ListMapperImpl(
                ComicMapper()
            )
        )
    )

    @Test
    fun `fetchMarvelEvents return empty list when no events`() = runBlockingTest {
        val repo = EventsRepository(mockMarvelWebService, realListMapper)
        stubWebServiceFetchCharacters(emptyList())
        val response = repo.fetchMarvelEvents()
        assertThat((response as NetworkResponse.Success).body).isEmpty()
    }

    @Test
    fun `fetchMarvelEvents return list of available events`() = runBlockingTest {
        val repo = EventsRepository(mockMarvelWebService, realListMapper)
        val eventDTO = EventDTOBuilder().build()
        stubWebServiceFetchCharacters(listOf(eventDTO))
        val response = repo.fetchMarvelEvents()
        assertThat((response as NetworkResponse.Success).body[0].id).isEqualTo(eventDTO.id)
    }

    @Test
    fun `fetchMarvelEvents return list of comics for each event`() = runBlockingTest {
        val repo = EventsRepository(mockMarvelWebService, realListMapper)

        val id = 20
        val name = "Thanos Comic"
        val eventDTO = EventDTOBuilder().setNewComics(ComicListDTO(
            listOf(ComicListDTO.ComicSummaryDTO("ur/$id", name)))).build()
        stubWebServiceFetchCharacters(listOf(eventDTO))

        val response = repo.fetchMarvelEvents() as NetworkResponse.Success
        assertThat(response.body[0].comics[0].id).isEqualTo(id)
    }

    @Test
    fun `when network response is ApiError, fetch events return the error`() = runBlockingTest {
        val error = NetworkResponse.ApiError(GenericApiError(500, "error"))
        stubWebServiceApiError(error)
        val repo = EventsRepository(mockMarvelWebService, realListMapper)
        val response = repo.fetchMarvelEvents()
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun `when network response is NetworkError, fetch events return the error`() = runBlockingTest {
        val error = NetworkResponse.NetworkError(IOException())
        stubWebServiceNetworkError(error)
        val repo = EventsRepository(mockMarvelWebService, realListMapper)
        val response = repo.fetchMarvelEvents()
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun `when network response is OtherError, fetch events return the error`() = runBlockingTest {
        val error = NetworkResponse.OtherError(null)
        stubWebServiceOtherError(error)
        val repo = EventsRepository(mockMarvelWebService, realListMapper)
        val response = repo.fetchMarvelEvents()
        assertThat(response).isEqualTo(error)
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