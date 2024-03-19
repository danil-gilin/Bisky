package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen

import com.example.bisky.common.model.BaseItem

interface AllGenreView {
    data class State(
        val isLoading: Boolean = false,
        val items: List<BaseItem> = emptyList(),
        val positionScroll: Int = 0
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
        data object OnGetMore : Event()
    }
}
