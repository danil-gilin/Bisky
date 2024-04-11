package com.example.bisky.ui.screen.searchpage.quicksearch.model

import com.example.bisky.common.model.BaseItem

data class AnimeFrontInfoUI(
    override val itemId: String,
    val logo: String,
    val name: String
): BaseItem {
    companion object {
        val previewItemAnimeFront =  AnimeFrontInfoUI(
            itemId = "anime",
            logo = "2",
            name = "Vanya hsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssui"
        )
    }
}
