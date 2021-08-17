package com.saj.marvel.utils

import okhttp3.mockwebserver.MockResponse
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

object WebServiceUtil {

    fun readJsonResponseAsString(fileName: String) : String {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-responses/$fileName")
        val buffer = inputStream?.source()?.buffer() ?: throw Exception()
        return buffer.readString(StandardCharsets.UTF_8)
    }

    fun getMockResponse(responseAsString: String, statusCode: Int): MockResponse {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(statusCode)
        mockResponse.setBody(responseAsString)
        return mockResponse
    }

}