package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.model

import com.example.bisky.common.model.BaseItem

data class GenreUI(
    override val itemId: String,
    val name: String,
    val description: String,
    val posters: List<Any>
) : BaseItem
