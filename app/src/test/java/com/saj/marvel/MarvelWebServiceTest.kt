package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterDTOBuilder
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

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch show characters correctly given 200 response`() = runBlocking {
        mockWebServer.enqueue(getMockResponse(readJsonResponseAsString("1-character-200.json"),
            200))

        val actual = webService.fetchMarvelCharacters().data.results
        val expected = listOf(
            CharacterDTOBuilder()
                .setId(1)
                .setName("Thanos")
                .build()
        )

        assertThat(expected).isEqualTo(actual)
    }
}