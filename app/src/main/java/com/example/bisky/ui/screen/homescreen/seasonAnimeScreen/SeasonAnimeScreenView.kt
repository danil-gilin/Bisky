package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen

import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.AnimeSeasonUI

sealed interface SeasonAnimeScreenView {
    data class State(
        val seasonImg: Int = 0,
        val seasonTitle: Int = 0,
        val positionScroll: Int = 0,
        val isLoading: Boolean = false,
        val itemsAnime: List<AnimeSeasonUI> = emptyList()
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
    }

    sealed class UiLabel
}
