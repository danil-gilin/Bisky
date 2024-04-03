package com.example.bisky.data.genre

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.genre.local.GenreDao
import com.example.bisky.data.genre.local.GenreLocalSourceImpl
import com.example.bisky.data.genre.remote.GenreRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.room.AppDatabase
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.local.GenreLocalSource
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
        genreLocalSource: GenreLocalSource,
        resultWrapper: ResultWrapper
    ): GenreRepository =
        GenreRepositoryImpl(genreRemoteSource, genreLocalSource, resultWrapper)

    @Singleton
    @Provides
    fun provideGenreRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): GenreRemoteSource =
        GenreRemoteSourceImpl(apolloClient, dispatchersProvider)

    @Singleton
    @Provides
    fun provideGenreLocalSource(
        genreDao: GenreDao,
        dispatchersProvider: DispatchersProvider
    ): GenreLocalSource =
        GenreLocalSourceImpl(genreDao, dispatchersProvider)

    @Singleton
    @Provides
    fun provideGenreDao(appDatabase: AppDatabase) = appDatabase.genreDao()
}