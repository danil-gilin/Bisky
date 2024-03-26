package com.example.bisky.data.network

import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.example.bisky.data.login.local.TokenPreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeaderApolloInterceptor @Inject constructor(
    private val tokenPreference: TokenPreference
) : ApolloInterceptor {
    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        val builder = request.newBuilder().apply {
            val accessToken =
                tokenPreference.getAccessToken()
                    ?: tokenPreference.getRefreshToken().orEmpty()
            addHttpHeader("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}
