package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model

import com.example.bisky.common.model.BaseItem

data class AnimeSeasonUI(
    override val itemId: String,
    val img: String,
    val title: String,
    val description: String,
    val rating: String,
    val isRatingVisible: Boolean,
    val ratingColor: Int,
    val genre: String,
    val backgroundImg: String
) : BaseItem
