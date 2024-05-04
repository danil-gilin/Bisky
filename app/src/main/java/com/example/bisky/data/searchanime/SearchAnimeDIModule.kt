package com.example.bisky.data.searchanime

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.anime.remote.AnimeApi
import com.example.bisky.data.genre.GenreRepositoryImpl
import com.example.bisky.data.genre.remote.GenreRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.searchanime.remote.SearchAnimeRemoteSourceImpl
import com.example.bisky.data.searchanime.remote.SearchApi
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.remote.GenreRemoteSource
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.domain.repository.searchanime.remote.SearchAnimeRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SearchAnimeDIModule {
    @Singleton
    @Provides
    fun provideSearchAnimeRepository(
        searchAnimeRemoteSource: SearchAnimeRemoteSource,
        resultWrapper: ResultWrapper
    ): SearchAnimeRepository =
        SearchAnimeRepositoryImpl(searchAnimeRemoteSource, resultWrapper)

    @Singleton
    @Provides
    fun provideSearchAnimeRemoteSource(
        apolloClient: ApolloClient,
        searchApi: SearchApi,
        dispatchersProvider: DispatchersProvider
    ): SearchAnimeRemoteSource =
        SearchAnimeRemoteSourceImpl(apolloClient, dispatchersProvider, searchApi)

    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit) = retrofit.create(SearchApi::class.java)
}