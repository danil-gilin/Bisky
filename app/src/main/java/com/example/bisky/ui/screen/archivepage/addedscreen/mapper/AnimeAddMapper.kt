package com.example.bisky.ui.screen.archivepage.addedscreen.mapper

import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.bisky.ui.screen.archivepage.addedscreen.model.AnimeAddUI
import javax.inject.Inject

class AnimeAddMapper @Inject constructor() {

    fun mapToUI(items: List<AnimeUserCollection>) = items.map {
        AnimeAddUI(
            itemId = it.id,
            name = it.name,
            poster = it.poster
        )
    }
}