package com.example.bisky.data.network

import com.example.bisky.data.login.local.TokenPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
   private val tokenPreference: TokenPreference
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().apply {
            val accessToken =
                tokenPreference.getAccessToken() ?:
                tokenPreference.getRefreshToken().orEmpty()
            addHeader("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}