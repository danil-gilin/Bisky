package com.example.bisky.ui.screen.archivepage.addedscreen

import com.example.bisky.common.model.BaseItem
import com.example.bisky.ui.screen.archivepage.container.model.QuickSelectItem
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView

interface AddScreenView {
    data class State(
        val isLoading: Boolean = true,
        val isLoadingPagging: Boolean = true,
        val positionScroll: Int = 0,
        val items: List<BaseItem> = emptyList(),
        val quickSelectUI: QuickSelectItem = QuickSelectItem()
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
        object OnRefresh: Event()
        object OnSearchClick : Event()
        object OnGetMore : Event()
    }
}