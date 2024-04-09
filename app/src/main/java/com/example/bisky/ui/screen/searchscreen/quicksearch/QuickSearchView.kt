package com.example.bisky.ui.screen.searchscreen.quicksearch

import com.example.bisky.ui.screen.searchscreen.quicksearch.model.AnimeBackInfoUI
import com.example.bisky.ui.screen.searchscreen.quicksearch.model.AnimeFrontInfoUI

interface QuickSearchView {
    data class State(
        val isLoading: Boolean = false,
        val isBackVisible: Boolean = false,
        val frontAnimeInfo: AnimeFrontInfoUI? = null,
        val backAnimeInfo: AnimeBackInfoUI? = null,
        val count : String = "6"
    )

    sealed class Event {
        data object OnLikeClick: Event()
        data object OnDislikeClick: Event()
        data object OnBackClick: Event()
    }

    sealed class Action {
        data object ShowBottomNav: Action()
    }
}