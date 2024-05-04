package com.example.bisky.ui.screen.searchpage.quicksearch

import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeBackInfoUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeFrontInfoUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.ControlButtonUI

interface QuickSearchView {
    data class State(
        val isAddedLoading: Boolean = false,
        val isInitLoading: Boolean = true,
        val isFinishedScreenVisible: Boolean = false,
        val isErrorScreenVisible: Boolean = false,
        val isAnimeScreenVisible: Boolean = false,
        val frontAnimeInfo: AnimeFrontInfoUI? = null,
        val backAnimeInfo: AnimeBackInfoUI? = null,
        val controlButtonUI: ControlButtonUI = ControlButtonUI.state,
    )

    sealed class Event {
        data class OnLikeClick(val id: String): Event()
        data class OnDislikeClick(val id: String): Event()
        data object OnBackClick: Event()
        data object OnMoreInfoClick: Event()
    }

    sealed class Action {
        data object ShowBottomNav: Action()
    }
}