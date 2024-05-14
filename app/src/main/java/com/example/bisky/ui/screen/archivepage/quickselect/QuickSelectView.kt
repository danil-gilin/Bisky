package com.example.bisky.ui.screen.archivepage.quickselect

import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.ui.screen.archivepage.quickselect.model.SelectAnimeUI


interface QuickSelectView {
    data class State(
        val isLoading: Boolean = true,
        val rightAnimeUI: SelectAnimeUI = SelectAnimeUI.default,
        val leftAnimeUI: SelectAnimeUI = SelectAnimeUI.default,
        val allCountSelectedAnime: Int = 30,
        val currentPositionSelectAnime: Int = 2,
        val isWinnerScreen: Boolean = false,
        val winner: SelectAnimeUI = SelectAnimeUI.default,
        val winnerIsRight: Boolean = false,
        val collection: CollectionAnime = CollectionAnime.NONE
    )

    sealed class Event {
        object OnRightSelect: Event()
        object OnPreviewSelectedClick: Event()
        object OnLeftSelect : Event()
    }

    sealed class Action {
        data object ShowBottomNav: Action()
    }
}