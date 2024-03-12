package com.example.bisky.data.network

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkDIModule {
    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient =
        ApolloClient.Builder()
            .serverUrl("https://bisky-back-rgnzzucls-amygrooove.vercel.app/graphql")
            .addInterceptor(LoggingApolloInterceptor())
            .build()
}
