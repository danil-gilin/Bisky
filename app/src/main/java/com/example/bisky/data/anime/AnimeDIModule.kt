package com.example.bisky.data.anime

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.anime.local.AnimeLocalSourceImpl
import com.example.bisky.data.anime.remote.AnimeRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.domain.repository.anime.remote.AnimeRemoteSource
import com.example.bisky.domain.repository.anime.local.AnimeLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AnimeDIModule {

    @Singleton
    @Provides
    fun provideAnimeRepository(
        animeClient: AnimeRemoteSource,
        animeLocalSource: AnimeLocalSource,
        resultWrapper: ResultWrapper
    ): AnimeRepository =
        AnimeRepositoryImpl(animeClient, animeLocalSource, resultWrapper)

    @Singleton
    @Provides
    fun provideAnimeRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): AnimeRemoteSource =
        AnimeRemoteSourceImpl(apolloClient, dispatchersProvider)

    @Singleton
    @Provides
    fun provideAnimeLocalSource(
        dispatchersProvider: DispatchersProvider
    ): AnimeLocalSource =
        AnimeLocalSourceImpl(dispatchersProvider)
}
