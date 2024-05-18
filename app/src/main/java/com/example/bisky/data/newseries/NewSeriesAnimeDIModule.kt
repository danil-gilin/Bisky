package com.example.bisky.data.newseries

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.genre.GenreRepositoryImpl
import com.example.bisky.data.genre.remote.GenreRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.newseries.remote.NewSeriesAnimeRemoteSourceImpl
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.local.GenreLocalSource
import com.example.bisky.domain.repository.genre.remote.GenreRemoteSource
import com.example.bisky.domain.repository.newseries.NewSeriesAnimeRemoteSource
import com.example.bisky.domain.repository.newseries.NewSeriesAnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewSeriesAnimeDIModule {

    @Singleton
    @Provides
    fun provideNewSeriesAnimeRepository(
        remoteSource: NewSeriesAnimeRemoteSource,
        resultWrapper: ResultWrapper
    ): NewSeriesAnimeRepository =
        NewSeriesAnimeRepositoryImpl(remoteSource, resultWrapper)

    @Singleton
    @Provides
    fun provideNewSeriesAnimeRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): NewSeriesAnimeRemoteSource =
        NewSeriesAnimeRemoteSourceImpl(apolloClient, dispatchersProvider)
}