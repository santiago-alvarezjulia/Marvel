package com.saj.marvel.network

import com.saj.marvel.BuildConfig
import com.saj.marvel.utils.AuthDigest
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class HttpAuthInterceptor(
    private val authDigest: AuthDigest
): Interceptor {

    companion object {
        private const val TIMESTAMP_QUERY_KEY = "ts"
        private const val APIKEY_QUERY_KEY = "apikey"
        private const val HASH_QUERY_KEY = "hash"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val publicApiKey = BuildConfig.PUBLIC_API_KEY
        val secretApiKey = BuildConfig.SECRET_API_KEY
        val hash = authDigest.generateDigest(ts+publicApiKey+secretApiKey)

        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(TIMESTAMP_QUERY_KEY, ts)
            .addQueryParameter(APIKEY_QUERY_KEY, publicApiKey)
            .addQueryParameter(HASH_QUERY_KEY, hash)
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}