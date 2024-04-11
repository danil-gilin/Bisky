package com.example.bisky.ui.screen.archivepage.watchedscreen.model

import com.example.bisky.common.model.BaseItem

data class AnimeWatchedUI(
    override val itemId: String,
    val poster: String,
    val name: String
): BaseItem {
    companion object {
        val preview = AnimeWatchedUI(
            "anime",
            "poster",
            "Anime/Anime"
        )
    }
}
