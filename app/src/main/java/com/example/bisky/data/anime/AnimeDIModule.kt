package com.example.bisky.data.anime

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.anime.local.AnimeLocalSourceImpl
import com.example.bisky.data.anime.remote.AnimeApi
import com.example.bisky.data.anime.remote.AnimeRemoteSourceImpl
import com.example.bisky.data.archive.local.AddCollectionDao
import com.example.bisky.data.archive.local.CompleteCollectionDao
import com.example.bisky.data.archive.local.WatchCollectionDao
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.domain.repository.anime.remote.AnimeRemoteSource
import com.example.bisky.domain.repository.anime.local.AnimeLocalSource
import com.example.bisky.domain.repository.archive.remote.CollectionRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AnimeDIModule {

    @Singleton
    @Provides
    fun provideAnimeApi(retrofit: Retrofit) = retrofit.create(AnimeApi::class.java)

    @Singleton
    @Provides
    fun provideAnimeRepository(
        animeClient: AnimeRemoteSource,
        resultWrapper: ResultWrapper
    ): AnimeRepository =
        AnimeRepositoryImpl(animeClient, resultWrapper)

    @Singleton
    @Provides
    fun provideAnimeRemoteSource(
        apolloClient: ApolloClient,
        animeApi: AnimeApi,
        dispatchersProvider: DispatchersProvider
    ): AnimeRemoteSource =
        AnimeRemoteSourceImpl(apolloClient, animeApi, dispatchersProvider)

    @Singleton
    @Provides
    fun provideAnimeLocalSource(
        dispatchersProvider: DispatchersProvider,
        addCollectionDao: AddCollectionDao,
        watchCollectionDao: WatchCollectionDao,
        completeCollectionDao: CompleteCollectionDao
    ): AnimeLocalSource =
        AnimeLocalSourceImpl(
            dispatchersProvider,
            addCollectionDao,
            watchCollectionDao,
            completeCollectionDao
        )
}
