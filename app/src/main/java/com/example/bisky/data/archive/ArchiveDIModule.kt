package com.example.bisky.data.archive

import com.apollographql.apollo3.ApolloClient
import com.example.bisky.data.archive.local.AddCollectionDao
import com.example.bisky.data.archive.local.ArchiveLocalSourceImpl
import com.example.bisky.data.archive.local.CompleteCollectionDao
import com.example.bisky.data.archive.local.WatchCollectionDao
import com.example.bisky.data.archive.remote.CollectionRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.room.AppDatabase
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.domain.repository.archive.local.CollectionLocalSource
import com.example.bisky.domain.repository.archive.remote.CollectionRemoteSource
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
        archiveRemoteSource: CollectionRemoteSource,
        archiveLocalSource: CollectionLocalSource,
        resultWrapper: ResultWrapper
    ): CollectionRepository =
        CollectionRepositoryImpl(archiveRemoteSource, archiveLocalSource, resultWrapper)

    @Singleton
    @Provides
    fun provideArchiveRemoteSource(
        apolloClient: ApolloClient,
        dispatchersProvider: DispatchersProvider
    ): CollectionRemoteSource =
        CollectionRemoteSourceImpl(apolloClient, dispatchersProvider)

    @Singleton
    @Provides
    fun provideArchiveLocalSource(
        dispatchersProvider: DispatchersProvider,
        addCollectionDao: AddCollectionDao,
        watchCollectionDao: WatchCollectionDao,
        completeCollectionDao: CompleteCollectionDao
    ): CollectionLocalSource =
        ArchiveLocalSourceImpl(
            dispatchersProvider,
            addCollectionDao,
            watchCollectionDao,
            completeCollectionDao
        )

    @Singleton
    @Provides
    fun provideAddCollectionDao(appDatabase: AppDatabase) = appDatabase.addCollectionDao()

    @Singleton
    @Provides
    fun provideWatchCollectionDao(appDatabase: AppDatabase) = appDatabase.watchCollectionDao()

    @Singleton
    @Provides
    fun provideCompleteCollectionDao(appDatabase: AppDatabase) = appDatabase.completeCollectionDao()
}