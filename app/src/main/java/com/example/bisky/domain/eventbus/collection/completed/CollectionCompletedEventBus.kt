package com.example.bisky.domain.eventbus.collection.completed

import kotlinx.coroutines.flow.Flow

interface CollectionCompletedEventBus {
    val eventsFlow: Flow<CollectionCompletedEventBusEvent>
    fun emitEvent(event: CollectionCompletedEventBusEvent)
}

sealed class CollectionCompletedEventBusEvent {
    data object UpdateCollection : CollectionCompletedEventBusEvent()
}