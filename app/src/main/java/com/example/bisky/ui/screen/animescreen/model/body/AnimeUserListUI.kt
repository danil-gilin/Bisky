package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class AnimeUserListUI(
    override val itemId: String,
    val addedCount: Int,
    val completedCount: Int,
    val droppedCount: Int,
    val generalCount: Int,
    val watchingCount: Int,
) : BaseItem
