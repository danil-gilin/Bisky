package com.example.bisky.ui.screen.archivepage.watchsreen

import com.example.bisky.common.model.BaseItem
import com.example.bisky.ui.screen.archivepage.addedscreen.AddScreenView
import com.example.bisky.ui.screen.archivepage.container.model.QuickSelectItem
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView
import com.example.bisky.ui.screen.archivepage.watchsreen.model.AnimeWatchUI

interface WatchScreenView {
    data class State(
        val isLoading: Boolean = false,
        val isLoadingPagging: Boolean = false,
        val positionScroll: Int = 0,
        val items: List<BaseItem> = emptyList(),
        val quickSelectUI: QuickSelectItem = QuickSelectItem(),
        val quickBtnEnabled: Boolean = false
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
        object OnRefresh : Event()
        object OnSearchClick : Event()
        object OnGetMore : Event()
    }
}