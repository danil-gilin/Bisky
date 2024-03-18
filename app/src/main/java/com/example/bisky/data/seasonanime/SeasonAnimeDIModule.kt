package com.example.bisky.data.seasonanime

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.room.AppDatabase
import com.example.bisky.data.seasonanime.local.SeasonAnimeDao
import com.example.bisky.data.seasonanime.local.SeasonAnimeLocalSourceImpl
import com.example.bisky.data.seasonanime.remote.SeasonAnimeRemoteSourceImpl
import com.example.bisky.domain.repository.seasonanime.SeasonAnimeRepository
import com.example.bisky.domain.repository.seasonanime.local.SeasonAnimeLocalSource
import com.example.bisky.domain.repository.seasonanime.remote.SeasonAnimeRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SeasonAnimeDIModule {

    @Singleton
    @Provides
    fun provideSeasonAnimeRepository(
        seasonAnimeClient: SeasonAnimeRemoteSource,
        seasonAnimeLocalSource: SeasonAnimeLocalSource,
        resultWrapper: ResultWrapper
    ): SeasonAnimeRepository =
        SeasonAnimeRepositoryImpl(seasonAnimeClient, seasonAnimeLocalSource, resultWrapper)

    @Singleton
    @Provides
    fun provideSeasonAnimeRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): SeasonAnimeRemoteSource =
        SeasonAnimeRemoteSourceImpl(apolloClient, dispatchersProvider)

    @Singleton
    @Provides
    fun provideSeasonAnimeLocalSource(
        seasonAnimeDao: SeasonAnimeDao,
        dispatchersProvider: DispatchersProvider
    ): SeasonAnimeLocalSource =
        SeasonAnimeLocalSourceImpl(seasonAnimeDao, dispatchersProvider)

    @Singleton
    @Provides
    fun provideSeasonAnimeDao(database: AppDatabase) = database.seasonAnimeDao()
}
