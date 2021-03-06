package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterDTOBuilder
import com.saj.marvel.builders.EventDTOBuilder
import com.saj.marvel.builders.ThumbnailDTOBuilder
import com.saj.marvel.network.GenericApiError
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.utils.MockWebService
import com.saj.marvel.utils.WebServiceUtil.getMockResponse
import com.saj.marvel.utils.WebServiceUtil.readJsonResponseAsString
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

class MarvelWebServiceTest {

    private val mockWebServer = MockWebServer()
    private val webService = MockWebService.getMockedWebService(mockWebServer)

    private val characterOffset = 0
    private val characterLimit = 15

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch characters correctly given 200 response`() = runBlocking {
        mockWebServer.enqueue(getMockResponse(readJsonResponseAsString("1-character-200.json"),
            200))

        val response = (webService.fetchPagedMarvelCharacters(characterLimit, characterOffset)
                as NetworkResponse.Success)
        val actual = response.body.data.results
        val expected = listOf(
            CharacterDTOBuilder()
                .setId(1)
                .setName("Thanos")
                .setDescription("The Mad Titan Thanos, a melancholy, brooding individual")
                .setThumbnail(ThumbnailDTOBuilder().build())
                .build()
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `when 500 response, should get code and status`() = runBlocking {
        val code = 500
        mockWebServer.enqueue(getMockResponse(readJsonResponseAsString("500.json"),
            code))

        val response = (webService.fetchPagedMarvelCharacters(characterLimit, characterOffset)
                as NetworkResponse.ApiError)
        val expected = NetworkResponse.ApiError(GenericApiError(code, "Internal Server Error"))

        assertThat(expected).isEqualTo(response)
    }

    @Test
    fun `should fetch events correctly given 200 response`() = runBlocking {
        mockWebServer.enqueue(getMockResponse(readJsonResponseAsString("1-event-200.json"),
            200))

        val response = webService.fetchMarvelEvents(1, "startDate")
        val actual = (response as NetworkResponse.Success).body.data.results
        val expected = listOf(
            EventDTOBuilder()
                .setId(1)
                .setName("Thanos Event")
                .setStartDate("2020-12-10 00:00:00")
                .setThumbnail(ThumbnailDTOBuilder().build())
                .build()
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `should fetch events correctly given 200 response with a startDate null`() = runBlocking {
        mockWebServer.enqueue(getMockResponse(readJsonResponseAsString("1-event-200-null-date.json"),
            200))

        val response = webService.fetchMarvelEvents(1, "startDate")
        val actual = (response as NetworkResponse.Success).body.data.results
        val expected = listOf(
            EventDTOBuilder()
                .setId(1)
                .setName("Thanos Event")
                .setStartDate(null)
                .setThumbnail(ThumbnailDTOBuilder().build())
                .build()
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `fetch characters given 200 response has paging data`() = runBlocking {

        val expectedTotal = 1533

        mockWebServer.enqueue(getMockResponse(readJsonResponseAsString("1-character-200.json"),
            200))

        val response = (webService.fetchPagedMarvelCharacters(characterLimit, characterOffset)
                as NetworkResponse.Success)
        val actualOffset = response.body.data.offset
        val actualCount = response.body.data.count
        val actualTotal = response.body.data.total

        assertThat(characterOffset).isEqualTo(actualOffset)
        assertThat(characterLimit).isEqualTo(actualCount)
        assertThat(expectedTotal).isEqualTo(actualTotal)
    }
}