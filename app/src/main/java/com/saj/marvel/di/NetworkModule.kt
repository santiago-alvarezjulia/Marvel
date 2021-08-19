package com.saj.marvel.di

import com.saj.marvel.BuildConfig
import com.saj.marvel.network.HttpAuthInterceptor
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.callAdapter.NetworkResponseAdapterFactory
import com.saj.marvel.utils.MD5Digest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMD5Digest() = MD5Digest()

    @Provides
    fun provideAuthInterceptor(mD5Digest: MD5Digest) = HttpAuthInterceptor(mD5Digest)

    @Provides
    fun provideOkHttpClient(httpAuthInterceptor: HttpAuthInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpAuthInterceptor)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideMarvelWebService(okHttpClient: OkHttpClient): MarvelWebService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .client(okHttpClient)
        .build()
        .create(MarvelWebService::class.java)

}