package com.example.bisky.data.archive

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.archive.remote.ArchiveRemoteSourceImpl
import com.example.bisky.data.genre.GenreRepositoryImpl
import com.example.bisky.data.genre.remote.GenreRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.archive.ArchiveRepository
import com.example.bisky.domain.repository.archive.remote.ArchiveRemoteSource
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
object ArchiveDIModule {
    @Singleton
    @Provides
    fun provideArchiveRepository(
        archiveRemoteSource: ArchiveRemoteSource,
        resultWrapper: ResultWrapper
    ): ArchiveRepository =
        ArchiveRepositoryImpl(archiveRemoteSource, resultWrapper)

    @Singleton
    @Provides
    fun provideArchiveRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): ArchiveRemoteSource =
        ArchiveRemoteSourceImpl(apolloClient, dispatchersProvider)
}