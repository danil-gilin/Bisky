package com.example.bisky.ui.screen.archivepage.quickselect.model

import com.example.bisky.R
import com.example.bisky.common.model.BaseItem

data class SelectAnimeUI(
    override val itemId: String,
    val name: String,
    val poster: Any,
    val backGroundImg: Any,
    val status: String,
    val statusColor: Int,
    val infoType:String,
    val infoDuration: String,
): BaseItem {

    companion object {
        val default = SelectAnimeUI(
            "fdaf",
            name = "anime",
            poster = R.drawable.ic_logo,
            backGroundImg = R.drawable.anime_autumn,
            status = "Вышел",
            statusColor = R.color.lime,
            infoType = "Сериал",
            infoDuration = "24 эп. по ~ 24 мин."
        )
    }
}
