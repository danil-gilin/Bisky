package com.example.bisky.data.searchanime.mapper

import com.example.bisky.ui.screen.searchpage.filterscreen.model.StatusAnimeFilter
import com.example.type.StatusEnum


fun List<StatusAnimeFilter>.mapToDto() = this.map {
    when (it) {
        StatusAnimeFilter.ANNOUNCED -> StatusEnum.anons
        StatusAnimeFilter.NOW -> StatusEnum.ongoing
        StatusAnimeFilter.PUBLISHED -> StatusEnum.released
        StatusAnimeFilter.RELEASED -> StatusEnum.released
    }
}
