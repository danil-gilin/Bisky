package com.example.bisky.ui.screen.homescreen.genre.genresscreen.model

import com.example.bisky.common.model.BaseItem

data class AnimeGenreUI(
    override val itemId: String,
    val name: String,
    val img: Any,
    val status: String,
    val statusColor: Int,
    val score: String,
    val scoreColor: Int,
    val isScoreVisible: Boolean
) : BaseItem
