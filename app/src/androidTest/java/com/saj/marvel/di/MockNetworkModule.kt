package com.saj.marvel.di

import com.saj.marvel.network.HttpAuthInterceptor
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.callAdapter.NetworkResponseAdapterFactory
import com.saj.marvel.utils.MD5Digest
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class MockNetworkModule {

    @Provides
    fun httpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideMD5Digest() = MD5Digest()

    @Provides
    fun provideAuthInterceptor(mD5Digest: MD5Digest) = HttpAuthInterceptor(mD5Digest)

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                            authInterceptor: HttpAuthInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    fun provideRickAndMortyWebService(okHttpClient: OkHttpClient): MarvelWebService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .baseUrl("http://localhost:8080/")
        .client(okHttpClient)
        .build()
        .create(MarvelWebService::class.java)
}