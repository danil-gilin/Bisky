package com.example.bisky.ui.screen.animeplayer

import com.example.bisky.common.model.BaseItem

interface AnimePlayerView {
    data class State(
        val url: String = "",
        val showWebView: Boolean = false
    )

    sealed class Action {
        object OnBackClick : Action()
    }
}