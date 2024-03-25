package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class AnimeScreenshotsUI(
    override val itemId: String,
    val list: List<Any>
) : BaseItem
