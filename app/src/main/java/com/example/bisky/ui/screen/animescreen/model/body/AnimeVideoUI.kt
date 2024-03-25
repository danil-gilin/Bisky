package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class AnimeVideoUI(
    override val itemId: String,
    val list: List<String>
) : BaseItem
