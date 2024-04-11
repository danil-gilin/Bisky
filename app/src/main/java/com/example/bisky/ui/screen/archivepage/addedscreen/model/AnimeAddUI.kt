package com.example.bisky.ui.screen.archivepage.addedscreen.model

import com.example.bisky.common.model.BaseItem

data class AnimeAddUI(
    override val itemId: String,
    val poster: String,
    val name: String
): BaseItem {
    companion object {
        val preview = AnimeAddUI(
            "anime",
            "poster",
            "Anime/Anime"
        )
    }
}
