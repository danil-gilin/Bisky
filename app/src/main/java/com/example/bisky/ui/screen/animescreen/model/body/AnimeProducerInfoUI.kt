package com.example.bisky.ui.screen.animescreen.model.body

import com.example.bisky.common.model.BaseItem

data class AnimeProducerInfoUI(
    override val itemId: String,
    val genres: String,
    val producer: String,
    val isProducerVisible: Boolean
) : BaseItem