package com.example.bisky.ui.screen.archivepage.watchsreen.model

import com.example.bisky.common.model.BaseItem

data class AnimeWatchUI(
    override val itemId: String,
    val poster: String,
    val name: String
): BaseItem {
    companion object {
        val preview = AnimeWatchUI(
            "anime",
            "poster",
            "Anime/Anime"
        )
    }
}
