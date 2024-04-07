package com.example.bisky.domain.eventbus.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationEventBusImpl: NavigationEventBus {

    private val mObservable = Channel<NavigationEventBusEvent>(capacity = CONFLATED)

    override val eventsFlow: Flow<NavigationEventBusEvent>
        get() = mObservable.receiveAsFlow()

    override fun emitEvent(event: NavigationEventBusEvent) {
        mObservable.trySend(event)
    }
}