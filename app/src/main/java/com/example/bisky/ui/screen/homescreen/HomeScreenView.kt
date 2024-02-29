package com.example.bisky.ui.screen.homescreen

import com.example.bisky.ui.screen.homescreen.model.AnimeSeasonUI

sealed interface HomeScreenView {
    data class State(
        val seasonImg: Int = 0,
        val seasonTitle: Int = 0,
        val itemsAnime: List<AnimeSeasonUI> = emptyList(),
    )

    sealed class Event {
    }

    sealed class UiLabel {
    }
}