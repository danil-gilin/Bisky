package com.example.bisky.ui.screen.homescreen.genre.genresscreen

import com.example.bisky.common.model.BaseItem

interface GenreScreenView {

    data class State(
        val isLoading: Boolean = false,
        val positionScroll: Int = 0,
        val title: String = "Genre",
        val items: List<BaseItem> = emptyList()
    )

    sealed class Event {
        data object OnGetMore : Event()
    }
}