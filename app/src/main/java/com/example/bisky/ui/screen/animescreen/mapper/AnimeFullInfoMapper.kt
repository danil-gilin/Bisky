package com.example.bisky.ui.screen.animescreen.mapper

import com.example.bisky.R
import com.example.bisky.common.model.BaseItem
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.ui.screen.animescreen.model.header.AnimeCardFullInfoUI
import com.example.bisky.ui.screen.animescreen.model.header.HeaderItemUI
import com.example.bisky.ui.screen.animescreen.model.header.InfoAnimeItemUI
import java.lang.Exception
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AnimeFullInfoMapper @Inject constructor() {

    fun map(anime: Anime): List<BaseItem> {
        val listItems = emptyList<BaseItem>()

        val header = anime.mapToHeader()
        return listItems.plus(header)

    }


    fun Anime.mapToHeader() = HeaderItemUI(
        itemId = this._id,
        screenshot = this.screenshots.firstOrNull() ?: R.drawable.ic_logo,
        name = this.label.orEmpty(),
        animeCard = this.mapToAnimeCard(),
        infoAnimeItemUI = this.mapToAnimeInfo()
    )


    fun Anime.mapToAnimeCard() = AnimeCardFullInfoUI(
        itemId = this._id + ANIME_CARD_PREFIX,
        poster = this.poster ?: R.drawable.ic_logo,
        score = this.score.averageScore.toString(),
        age = "14+",
        ageVisible = true,
        isScoreVisible = this.score.averageScore > 0.0,
        scoreColor = this.mapToScoreColor()
    )


    fun Anime.mapToScoreColor() = when (score.averageScore) {
        in 0.0..4.9 -> R.color.red
        in 5.0..7.8 -> R.color.gray
        else -> R.color.lime
    }

    fun Anime.mapToAnimeInfo() = InfoAnimeItemUI(
        itemId = this._id,
        collectionIcon = R.drawable.ic_watch_status,
        statusColor = this.mapToStatusColor(),
        infoStatus = this.mapToStatus(),
        infoDate = this.dates.formatDate(),
        infoDuration = this.mapToInfoDuration(),
        infoType = "${this.kind},"
    )

    fun Anime?.mapToInfoDuration(): String {
        val episodesDuration = this?.episodes?.averageDuration?.let {
            val hour = it / 60
            val minute = it % 60
            if (hour >0) {
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

    fun String?.formatDate(): String {
        return try {
            ZonedDateTime.parse(this).format(DateTimeFormatter.ofPattern(DATE_FORMAT))
        } catch (e: Exception) {
            "Нет информации"
        }
    }

    fun Anime.mapToStatus() = when (status) {
        RELEASED -> "вышел"
        ONGOING -> "идет"
        ANONS -> "анонс"
        else -> "Неизвестно"
    }

    fun Anime.mapToStatusColor() = when (status) {
        RELEASED -> R.color.green
        ONGOING -> R.color.blue
        ANONS -> R.color.orange
        else -> R.color.gray
    }


    companion object {
        private const val ANIME_CARD_PREFIX = "anime_card_prefix"
        private const val ANIME_INFO_PREFIX = "anime_info_prefix"
        private const val ANONS = "anons"
        private const val ONGOING = "ongoing"
        private const val RELEASED = "released"
        private const val UNKNOWN = "unknown"
        private const val DATE_FORMAT = "dd MMMM YYYY"
    }
}