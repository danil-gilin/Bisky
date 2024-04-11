package com.example.bisky.ui.screen.archivepage.watchedscreen.mapper

import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.bisky.ui.screen.archivepage.watchedscreen.model.AnimeWatchedUI
import javax.inject.Inject

class AnimeWatchedMapper @Inject constructor() {

    fun mapToUI(items: List<AnimeUserCollection>) = items.map {
        AnimeWatchedUI(
            itemId = it.id,
            name = it.name,
            poster = it.poster
        )
    }
}