package com.example.bisky.domain.eventbus.collection.watching

import kotlinx.coroutines.flow.Flow

interface CollectionWatchingEventBus {
    val eventsFlow: Flow<CollectionWatchingEventBusEvent>
    fun emitEvent(event: CollectionWatchingEventBusEvent)
}

sealed class CollectionWatchingEventBusEvent {
    data object UpdateCollection : CollectionWatchingEventBusEvent()
}