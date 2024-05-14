package com.example.bisky.domain.eventbus.collection.add

import kotlinx.coroutines.flow.Flow

interface CollectionAddEventBus {
    val eventsFlow: Flow<CollectionAddEventBusEvent>
    fun emitEvent(event: CollectionAddEventBusEvent)
}

sealed class CollectionAddEventBusEvent {
    data object UpdateCollection : CollectionAddEventBusEvent()
}