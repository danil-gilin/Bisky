package com.example.bisky.ui.screen.archivepage.watchsreen

import com.example.bisky.common.model.BaseItem
import com.example.bisky.ui.screen.archivepage.watchsreen.model.AnimeWatchUI

interface WatchScreenView {
    data class State(
        val isLoading: Boolean = true,
        val positionScroll: Int = 0,
        val items: List<BaseItem> = emptyList()
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
    }
}