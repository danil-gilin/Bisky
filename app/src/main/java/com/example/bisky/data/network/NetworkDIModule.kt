package com.example.bisky.data.network

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.login.local.TokenPreference
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.dispatcher.DispatchersProviderImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkDIModule {
    @Singleton
    @Provides
    fun provideApolloClient(
        loginLocalSourceImpl: TokenPreference
    ): ApolloClient =
        ApolloClient.Builder()
            .serverUrl("https://api.bisky.one/graphql")
            .addInterceptor(HeaderApolloInterceptor(loginLocalSourceImpl))
            .addInterceptor(LoggingApolloInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideOkHttpBuilder(
        loginLocalSourceImpl: TokenPreference
    ) = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(HeaderInterceptor(loginLocalSourceImpl))
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("https://api.bisky.one/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatchersProvider = DispatchersProviderImpl
}
