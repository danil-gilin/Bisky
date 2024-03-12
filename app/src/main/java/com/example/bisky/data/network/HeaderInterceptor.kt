package com.example.bisky.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {
    // TODO THINK ABOUT HEADER
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().apply {
            // addHeader(headerName, value)
        }
        return chain.proceed(builder.build())
    }
}