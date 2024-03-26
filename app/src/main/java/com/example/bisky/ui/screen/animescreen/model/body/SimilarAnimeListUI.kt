package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class SimilarAnimeListUI(
    override val itemId: String,
    val items: List<SimilarAnimeUI>
): BaseItem


data class SimilarAnimeUI(
    override val itemId: String,
    val poster: Any,
    val name: String,
    val rating: String,
    val ratingColor: Int,
    val isRatingVisible: Boolean
): BaseItem