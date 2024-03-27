package com.example.bisky.ui.screen.animescreen.model.header

import com.example.bisky.common.model.BaseItem

data class InfoAnimeItemUI(
    override val itemId: String,
    val infoDuration: String,
    val infoStatus: String,
    val statusColor: Int,
    val infoType: String,
    val infoDate: String,
    val collectionAdded: Int,
    val collectionCompleted: Int,
    val collectionDropped: Int,
    val collectionWatching: Int,
    val collectionNone: Int
) : BaseItem
