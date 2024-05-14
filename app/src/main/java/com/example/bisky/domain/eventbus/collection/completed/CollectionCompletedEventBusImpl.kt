package com.example.bisky.domain.eventbus.collection.completed

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class CollectionCompletedEventBusImpl: CollectionCompletedEventBus {

    private val mObservable =
        Channel<CollectionCompletedEventBusEvent>(capacity = Channel.CONFLATED)

    override val eventsFlow: Flow<CollectionCompletedEventBusEvent>
        get() = mObservable.receiveAsFlow()

    override fun emitEvent(event: CollectionCompletedEventBusEvent) {
        mObservable.trySend(event)
    }
}