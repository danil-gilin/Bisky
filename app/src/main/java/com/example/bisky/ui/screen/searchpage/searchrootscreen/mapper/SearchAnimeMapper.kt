package com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper

import com.example.bisky.R
import com.example.bisky.domain.repository.searchanime.model.AnimeSearch
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchAnimeUI
import javax.inject.Inject

class SearchAnimeMapper @Inject constructor() {

    fun mapToUI(items: List<AnimeSearch>) = items.map {
        SearchAnimeUI(
            itemId = it._id,
            name = it.label.orEmpty(),
            img = it.poster ?: R.drawable.ic_logo,
            score = it.scores.toString(),
            isScoreVisible = it.mapToScoreVisible(),
            scoreColor = it.mapToScoreColor(),
            status = it.mapToStatus(),
            statusColor = it.mapToStatusColor()
        )
    }


    fun AnimeSearch.mapToScoreVisible() = scores > 0.0
    fun AnimeSearch.mapToScoreColor() = when (scores) {
        in 0.0..4.9 -> R.color.red
        in 5.0..7.8 -> R.color.gray
        else -> R.color.lime
    }

    fun AnimeSearch.mapToStatus() = when (status) {
        null -> UNKNOWN
        else -> status
    }

    fun AnimeSearch.mapToStatusColor() = when (status) {
        RELEASED -> R.color.green
        ONGOING -> R.color.blue
        ANONS -> R.color.orange
        else -> R.color.gray
    }

    companion object {
        private const val ANONS = "anons"
        private const val ONGOING = "ongoing"
        private const val RELEASED = "released"
        private const val UNKNOWN = "unknown"
    }
}