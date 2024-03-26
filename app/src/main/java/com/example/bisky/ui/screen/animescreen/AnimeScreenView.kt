package com.example.bisky.ui.screen.animescreen

import com.example.bisky.common.model.BaseItem

interface AnimeScreenView {

    data class State(
        val isLoading: Boolean = false,
        val isFullDescription: Boolean = false,
        val items: List<BaseItem> = emptyList()
    )

    sealed class Event {
        data class OnClickFullDescription(val isFullInfoDescription: Boolean) : Event()
        data object OnDeleteScoreClick : Event()
        data class OnSelectScore(val selectedScore: Int) : Event()
        data object OnCompleteScore : Event()
    }
}
