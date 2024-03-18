package com.example.bisky.data.genre

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.genre.remote.GenreRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.remote.GenreRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GenreDIModule {
    @Singleton
    @Provides
    fun provideGenreRepository(
        genreRemoteSource: GenreRemoteSource,
        resultWrapper: ResultWrapper
    ): GenreRepository =
        GenreRepositoryImpl(genreRemoteSource, resultWrapper)

    @Singleton
    @Provides
    fun provideGenreRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): GenreRemoteSource =
        GenreRemoteSourceImpl(apolloClient, dispatchersProvider)
}