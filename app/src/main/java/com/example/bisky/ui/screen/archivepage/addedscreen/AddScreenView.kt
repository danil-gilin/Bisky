package com.example.bisky.ui.screen.archivepage.addedscreen

import com.example.bisky.common.model.BaseItem

interface AddScreenView {
    data class State(
        val isLoading: Boolean = true,
        val positionScroll: Int = 0,
        val items: List<BaseItem> = emptyList()
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
    }
}