package com.example.bisky.domain.eventbus.collection.add

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class CollectionAddEventBusImpl: CollectionAddEventBus {

    private val mObservable =
        Channel<CollectionAddEventBusEvent>(capacity = Channel.CONFLATED)

    override val eventsFlow: Flow<CollectionAddEventBusEvent>
        get() = mObservable.receiveAsFlow()

    override fun emitEvent(event: CollectionAddEventBusEvent) {
        mObservable.trySend(event)
    }
}