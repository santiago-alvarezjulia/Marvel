package com.saj.marvel.utils

import com.saj.marvel.network.HttpAuthInterceptor
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.callAdapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MockWebService {

    fun getMockedWebService(mockWebServer: MockWebServer) : MarvelWebService {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val authInterceptor = HttpAuthInterceptor(MD5Digest())

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MarvelWebService::class.java)
    }
}