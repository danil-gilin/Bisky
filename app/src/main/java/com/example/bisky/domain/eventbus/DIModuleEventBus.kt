package com.example.bisky.domain.eventbus

import com.example.bisky.domain.eventbus.collection.add.CollectionAddEventBus
import com.example.bisky.domain.eventbus.collection.add.CollectionAddEventBusImpl
import com.example.bisky.domain.eventbus.collection.completed.CollectionCompletedEventBus
import com.example.bisky.domain.eventbus.collection.completed.CollectionCompletedEventBusImpl
import com.example.bisky.domain.eventbus.collection.watching.CollectionWatchingEventBus
import com.example.bisky.domain.eventbus.collection.watching.CollectionWatchingEventBusImpl
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusImpl
import com.example.bisky.domain.eventbus.search.SearchEventBus
import com.example.bisky.domain.eventbus.search.SearchEventBusImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModuleEventBus {

    @Provides
    @Singleton
    fun provideNavigationEventBus() : NavigationEventBus = NavigationEventBusImpl()

    @Provides
    @Singleton
    fun provideSearchEventBus() : SearchEventBus = SearchEventBusImpl()

    @Provides
    @Singleton
    fun provideCollectionWatchingEventBus() : CollectionWatchingEventBus = CollectionWatchingEventBusImpl()

    @Provides
    @Singleton
    fun provideCollectionAddEventBus() : CollectionAddEventBus = CollectionAddEventBusImpl()

    @Provides
    @Singleton
    fun provideCollectionCompletedEventBus() : CollectionCompletedEventBus = CollectionCompletedEventBusImpl()
}
