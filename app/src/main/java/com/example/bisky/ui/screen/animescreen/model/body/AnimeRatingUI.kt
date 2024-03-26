package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class AnimeRatingUI(
    override val itemId: String,
    val ratingCount: String,
    val rating: String,
    val ratingColor: Int,
    val isRatingVisible: Boolean,
    val ratingUser: String,
    val ratingColorUser: Int,
    val isRatingVisibleUser: Boolean,
    val selectedScore: Int
) : BaseItem
