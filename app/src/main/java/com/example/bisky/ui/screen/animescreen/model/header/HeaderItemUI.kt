package com.example.bisky.ui.screen.animescreen.model.header

import com.example.bisky.common.model.BaseItem

data class HeaderItemUI(
    override val itemId: String,
    val name: String,
    val screenshot: Any,
    val infoAnimeItemUI: InfoAnimeItemUI,
    val animeCard: AnimeCardFullInfoUI
) : BaseItem

data class AnimeCardFullInfoUI(
    override val itemId: String,
    val poster: Any,
    val score: String,
    val scoreColor: Int,
    val isScoreVisible: Boolean,
    val age: String,
    val ageVisible: Boolean
) : BaseItem
