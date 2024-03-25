package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class AnimeDescriptionUI(
    override val itemId: String,
    val text: String,
    val isFullInfo : Boolean
) : BaseItem
