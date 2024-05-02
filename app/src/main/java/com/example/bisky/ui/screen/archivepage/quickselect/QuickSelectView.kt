package com.example.bisky.ui.screen.archivepage.quickselect

import com.example.bisky.ui.screen.archivepage.quickselect.model.SelectAnimeUI


interface QuickSelectView {
    data class State(
        val isLoading: Boolean = true,
        val rightAnimeUI: SelectAnimeUI = SelectAnimeUI.default,
        val leftAnimeUI: SelectAnimeUI = SelectAnimeUI.default,
        val allCountSelectedAnime: Int = 30,
        val currentPositionSelectAnime: Int = 2,
    )

    sealed class Event {
        object OnRightSelect: Event()
        object OnPreviewSelectedClick: Event()
        object OnLeftSelect : Event()
    }
}