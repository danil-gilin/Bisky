package com.example.bisky.ui.screen.searchpage.quicksearch.mapper

import android.content.Context
import com.example.bisky.R
import com.example.bisky.common.ext.saveSubList
import com.example.bisky.domain.repository.searchanime.model.AnimeQuickSearch
import com.example.bisky.ui.screen.animescreen.mapper.AnimeFullInfoMapper
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeBackInfoUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeDescriptionUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeFrontInfoUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeStatusSelect
import com.example.bisky.ui.screen.searchpage.quicksearch.model.ControlButtonUI
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class QuickSearchAnimeMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun mapToControlButtonUI(
        count: Int,
        maxCount: Int,
        animeStatusSelect: AnimeStatusSelect?
    ) = ControlButtonUI(
        count = count.toString(),
        isCountVisible = count != 1,
        isBackVisible = count < maxCount,
        backGroundDislikeColor = animeStatusSelect.mapToBtnColorBackground(AnimeStatusSelect.Dislike),
        backGroundLikeColor = animeStatusSelect.mapToBtnColorBackground(AnimeStatusSelect.Like),
        likeColor = animeStatusSelect.mapToBtnColorIcon(AnimeStatusSelect.Like),
        dislikeColor = animeStatusSelect.mapToBtnColorIcon(AnimeStatusSelect.Dislike),
    )

    private fun AnimeStatusSelect?.mapToBtnColorBackground(compareStatus: AnimeStatusSelect) =
        if (this == compareStatus) {
            R.color.bisky_400_alpha_70
        } else {
            R.color.bisky_dark_200_alpha_60
        }


    private fun AnimeStatusSelect?.mapToBtnColorIcon(compareStatus: AnimeStatusSelect) =
        if (this == compareStatus) {
            R.color.bisky_300
        } else {
            R.color.bisky_300
        }

    fun mapToAnimeBackUI(
        anime: AnimeQuickSearch
    ) =
        AnimeBackInfoUI(
            itemId = anime._id,
            statusColor = anime.mapToStatusColor(),
            infoStatus = anime.mapToStatus(),
            infoDate = anime.dates.formatDate(),
            infoDuration = anime.mapToInfoDuration(),
            infoProducer = anime.studios.joinToString(", ") { it.name },
            infoGenre = anime.genres.joinToString(", "),
            infoType = "${anime.kind},",
            isScoreVisible = anime.score.averageScore > 0.0,
            isAgeVisible = true,
            screenshotList = anime.screenshots.saveSubList(0, 3),
            descriptionUI = anime.mapToDescription(),
            score = anime.score.averageScore.toString(),
            scoreColor = anime.score.averageScore.mapToScoreColor(),
            age = anime.age
        )

    fun mapToAnimeFrontUI(
        animeQuickSearches: AnimeQuickSearch
    ) =
        AnimeFrontInfoUI(
            itemId = animeQuickSearches._id,
            logo = animeQuickSearches.poster.orEmpty(),
            name = animeQuickSearches.label.orEmpty()
        )

    fun updateFullDescription(animeBackInfoUI: AnimeBackInfoUI?) =
        animeBackInfoUI?.copy(
            descriptionUI = animeBackInfoUI.descriptionUI?.copy(
                isFullInfo = !animeBackInfoUI.descriptionUI.isFullInfo
            )
        )

    private fun AnimeQuickSearch.mapToDescription(): AnimeDescriptionUI? {
        val description = this.description
        if (description.isNullOrEmpty()) return null
        val isFullDescription = description.length < 250
        return AnimeDescriptionUI(
            itemId = AnimeFullInfoMapper.ANIME_DESCRIPTION_ID,
            text = description,
            isFullInfo = isFullDescription
        )
    }

    private fun Double.mapToScoreColor() = when (this) {
        in 0.0..4.9 -> R.color.red
        in 5.0..7.8 -> R.color.gray
        else -> R.color.lime
    }

    private fun AnimeQuickSearch?.mapToInfoDuration(): String {
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

    private fun String?.formatDate(): String {
        return try {
            ZonedDateTime.parse(this).format(DateTimeFormatter.ofPattern(DATE_FORMAT))
        } catch (e: Exception) {
            "Нет информации"
        }
    }

    private fun AnimeQuickSearch.mapToStatus() = when (status) {
        RELEASED -> "вышел"
        ONGOING -> "идет"
        ANONS -> "анонс"
        else -> "Неизвестно"
    }

    private fun AnimeQuickSearch.mapToStatusColor() = when (status) {
        RELEASED -> R.color.green
        ONGOING -> R.color.blue
        ANONS -> R.color.orange
        else -> R.color.gray
    }

    companion object {
        private const val ANONS = "anons"
        private const val ONGOING = "ongoing"
        private const val RELEASED = "released"
        private const val DATE_FORMAT = "dd MMMM YYYY"
    }
}