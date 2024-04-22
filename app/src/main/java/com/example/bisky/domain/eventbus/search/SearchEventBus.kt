package com.example.bisky.domain.eventbus.search

import kotlinx.coroutines.flow.Flow

interface SearchEventBus {
    val eventsFlow: Flow<SearchEventBusEvent>
    fun emitEvent(event: SearchEventBusEvent)
}

sealed class SearchEventBusEvent {
    data object SearchAnime : SearchEventBusEvent()
}