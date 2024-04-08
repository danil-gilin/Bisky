package com.example.bisky.ui.screen.searchscreen.quicksearch

interface QuickSearchView {
    data class State(
        val isLoading: Boolean = false,
        val count : String = "6"
    )

    sealed class Event {

    }

    sealed class Action {
        data object ShowBottomNav: Action()
    }
}