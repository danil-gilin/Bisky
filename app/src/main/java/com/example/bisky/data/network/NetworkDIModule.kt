package com.example.bisky.data.network

import com.apollographql.apollo3.ApolloClient
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
    fun provideApolloClient(): ApolloClient =
        ApolloClient.Builder()
            .serverUrl("https://bisky-back-rgnzzucls-amygrooove.vercel.app/graphql")
            .addInterceptor(LoggingApolloInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideOkHttpBuilder() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("https://bisky-back-rgnzzucls-amygrooove.vercel.app/") // TODO ADD BASE URL
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
