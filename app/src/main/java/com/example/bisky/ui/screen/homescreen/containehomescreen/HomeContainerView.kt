package com.example.bisky.ui.screen.homescreen.containehomescreen

import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.HomeTab
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.HomeTabType

sealed interface HomeContainerView {
    data class State(
        val listTab: List<HomeTab> = listOf(
            HomeTab(name = R.string.title_new_series, isSelected = false, type = HomeTabType.New),
            HomeTab(name = R.string.title_season, isSelected = true, type = HomeTabType.Season),
            HomeTab(name = R.string.title_genre, isSelected = false, type = HomeTabType.Genre)
        ),
        val currentTabType: HomeTabType = HomeTabType.Season
    )

    sealed class Event {
        data class OnTabSelected(val position: HomeTabType) : Event()
    }

    sealed class UiLabel
}
