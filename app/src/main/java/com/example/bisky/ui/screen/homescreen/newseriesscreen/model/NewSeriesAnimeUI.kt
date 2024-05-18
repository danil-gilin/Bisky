package com.example.bisky.ui.screen.homescreen.newseriesscreen.model

import com.example.bisky.common.model.BaseItem

data class NewSeriesAnimeUI(
    override val itemId: String,
    val poster: String,
    val name: String,
    val infoSeries: String
): BaseItem {
    companion object {
        val preview = NewSeriesAnimeUI(
            "anime",
            "poster",
            "Anime/Anime",
            "19 серия"
        )
    }
}
