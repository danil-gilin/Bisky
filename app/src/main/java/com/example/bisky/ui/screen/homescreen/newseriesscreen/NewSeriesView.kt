package com.example.bisky.ui.screen.homescreen.newseriesscreen

import com.example.bisky.common.model.BaseItem
import com.example.bisky.ui.screen.homescreen.newseriesscreen.model.NewSeriesAnimeUI
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.AnimeSeasonUI

interface NewSeriesView {
    data class State(
        val positionScroll: Int = 0,
        val isLoading: Boolean = false,
        val itemsAnime: List<BaseItem> = emptyList(),
        val isEmptyState: Boolean = false
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
        data object OnGetMore: Event()
    }
}