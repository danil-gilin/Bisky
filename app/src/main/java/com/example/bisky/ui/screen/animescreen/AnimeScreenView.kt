package com.example.bisky.ui.screen.animescreen

import com.example.bisky.common.model.BaseItem

interface AnimeScreenView {

    data class State(
        val isLoading: Boolean = false,
        val items: List<BaseItem> = emptyList()
    )

    sealed class Event {
        data object OnGetMore : Event()
    }
}
