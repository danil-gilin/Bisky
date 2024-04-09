package com.example.bisky.ui.screen.animescreen.model.header

import com.example.bisky.common.model.BaseItem

data class InfoAnimeItemUI(
    override val itemId: String,
    val infoDuration: String,
    val infoStatus: String,
    val statusColor: Int,
    val infoType: String,
    val infoDate: String,
    val imgCollectionAdded: Int,
    val imgCollectionCompleted: Int,
    val imgCollectionDropped: Int,
    val imgCollectionWatching: Int,
    val imgCollectionNone: Int
) : BaseItem
