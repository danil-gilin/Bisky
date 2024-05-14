package com.example.bisky.domain.eventbus.collection.watching

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class CollectionWatchingEventBusImpl: CollectionWatchingEventBus {

    private val mObservable =
        Channel<CollectionWatchingEventBusEvent>(capacity = Channel.CONFLATED)

    override val eventsFlow: Flow<CollectionWatchingEventBusEvent>
        get() = mObservable.receiveAsFlow()

    override fun emitEvent(event: CollectionWatchingEventBusEvent) {
        mObservable.trySend(event)
    }
}