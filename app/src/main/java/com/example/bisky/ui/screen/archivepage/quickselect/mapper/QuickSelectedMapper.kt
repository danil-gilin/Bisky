package com.example.bisky.ui.screen.archivepage.quickselect.mapper

import android.content.Context
import com.example.bisky.R
import com.example.bisky.domain.repository.archive.model.AnimeQuickSelect
import com.example.bisky.domain.repository.searchanime.model.AnimeQuickSearch
import com.example.bisky.ui.screen.archivepage.quickselect.model.SelectAnimeUI
import com.example.bisky.ui.screen.searchpage.quicksearch.mapper.QuickSearchAnimeMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class QuickSelectedMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun mapSelectAnimeToUI(animeQuickSelect: AnimeQuickSelect) =
        SelectAnimeUI(
            itemId = animeQuickSelect.id,
            name = animeQuickSelect.label.orEmpty(),
            poster = animeQuickSelect.poster ?: R.drawable.ic_logo,
            backGroundImg = animeQuickSelect.screenshots.firstOrNull() ?: animeQuickSelect.poster ?: R.drawable.ic_logo,
            statusColor =  animeQuickSelect.mapToStatusColor(),
            status = animeQuickSelect.mapToStatus(),
            infoDuration = animeQuickSelect.mapToInfoDuration(),
            infoType = "${animeQuickSelect.kind},",
        )


    private fun AnimeQuickSelect?.mapToInfoDuration(): String {
        val episodesDuration = this?.episodes?.averageDuration?.let {
            val hour = it / 60
            val minute = it % 60
            if (hour > 0) {
                "$hour ч, $minute мин"
            } else {
                "$minute мин"
            }
        }
        return when {
            this?.episodes?.count == null && this?.episodes?.averageDuration == null -> "Нет информации"
            this.episodes.count == null -> "эп.по ~ $episodesDuration"
            this.episodes.averageDuration == null -> "${this.episodes.count} эп."
            else -> "${this.episodes.count} эп.по ~ $episodesDuration"
        }
    }

    private fun AnimeQuickSelect.mapToStatus() = when (status) {
        RELEASED -> "вышел"
        ONGOING -> "идет"
        ANONS -> "анонс"
        else -> "Неизвестно"
    }

    private fun AnimeQuickSelect.mapToStatusColor() = when (status) {
        RELEASED -> R.color.green
        ONGOING -> R.color.blue
        ANONS -> R.color.orange
        else -> R.color.gray
    }.let {
        context.getColor(it)
    }

    companion object {
        private const val ANONS = "anons"
        private const val ONGOING = "ongoing"
        private const val RELEASED = "released"
    }
}