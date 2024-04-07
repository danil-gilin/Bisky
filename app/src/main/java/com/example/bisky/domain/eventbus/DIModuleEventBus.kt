package com.example.bisky.domain.eventbus

import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusImpl
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
}
