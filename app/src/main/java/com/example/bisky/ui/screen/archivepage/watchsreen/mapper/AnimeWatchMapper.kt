package com.example.bisky.ui.screen.archivepage.watchsreen.mapper

import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.bisky.ui.screen.archivepage.watchsreen.model.AnimeWatchUI
import javax.inject.Inject

class AnimeWatchMapper @Inject constructor() {

    fun mapToUI(items: List<AnimeUserCollection>) = items.map {
        AnimeWatchUI(
            itemId = it.id,
            name = it.name,
            poster = it.poster
        )
    }
}