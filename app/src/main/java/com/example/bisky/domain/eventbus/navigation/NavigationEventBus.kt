package com.example.bisky.domain.eventbus.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationEventBus {
    val eventsFlow: Flow<NavigationEventBusEvent>
    fun emitEvent(event: NavigationEventBusEvent)
}

sealed class NavigationEventBusEvent {
    data class ChangeVisibleBottomNav(val isVisible: Boolean) : NavigationEventBusEvent()
}