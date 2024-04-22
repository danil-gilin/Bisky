package com.example.bisky.domain.eventbus.search

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class SearchEventBusImpl : SearchEventBus {

    private val mObservable = Channel<SearchEventBusEvent>(capacity = Channel.CONFLATED)

    override val eventsFlow: Flow<SearchEventBusEvent>
        get() = mObservable.receiveAsFlow()

    override fun emitEvent(event: SearchEventBusEvent) {
        mObservable.trySend(event)
    }
}