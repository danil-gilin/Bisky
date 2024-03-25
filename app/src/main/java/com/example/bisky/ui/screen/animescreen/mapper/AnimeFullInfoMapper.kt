package com.example.bisky.ui.screen.animescreen.mapper

import com.example.bisky.R
import com.example.bisky.common.model.BaseItem
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.ui.screen.animescreen.model.body.AnimeDescriptionUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeProducerInfoUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeScreenshotsUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeUserListUI
import com.example.bisky.ui.screen.animescreen.model.header.AnimeCardFullInfoUI
import com.example.bisky.ui.screen.animescreen.model.header.HeaderItemUI
import com.example.bisky.ui.screen.animescreen.model.header.InfoAnimeItemUI
import java.lang.Exception
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AnimeFullInfoMapper @Inject constructor() {

    fun map(anime: Anime): List<BaseItem> {
        val listItems = mutableListOf<BaseItem>()

        val header = anime.mapToHeader()
        listItems.add(header)
        val description = anime.mapToDescription()
        description?.let { listItems.add(it) }

        val producerInfo = anime.mapToProducerInfo()
        listItems.add(producerInfo)

        val userlistInfo = anime.mapToUserList()
        listItems.add(userlistInfo)

        val screenshotInfo = anime.mapToScreenshots()
        screenshotInfo?.let { listItems.add(it) }


        return listItems
    }

    fun updateDescriptionItem(
        item: List<BaseItem>,
        isFullInfo: Boolean
    ): List<BaseItem> {
      val descriptionUi = item.find { it.itemId == ANIME_DESCRIPTION_ID } as? AnimeDescriptionUI ?: return item
      return item.map {
          if (it.itemId == ANIME_DESCRIPTION_ID) {
              descriptionUi.copy(isFullInfo = isFullInfo)
          } else {
              it
          }
      }
    }


    fun Anime.mapToHeader() = HeaderItemUI(
        itemId = this._id,
        screenshot = this.screenshots.firstOrNull() ?: this.poster ?: R.drawable.ic_logo,
        name = this.label.orEmpty(),
        animeCard = this.mapToAnimeCard(),
        infoAnimeItemUI = this.mapToAnimeInfo()
    )

    fun Anime.mapToDescription(): AnimeDescriptionUI? {
        val description = this.description
        if (description.isNullOrEmpty()) return null
        val isFullDescription = description.length < 250
        return AnimeDescriptionUI(
            itemId = ANIME_DESCRIPTION_ID,
            text = description,
            isFullInfo = isFullDescription
        )
    }

    fun Anime.mapToProducerInfo(): AnimeProducerInfoUI {
        return AnimeProducerInfoUI(
            itemId = ANIME_PRODUCER_INFO,
            genres = this.genres.joinToString(", "),
            producer = this.franchise?.name.orEmpty(),
            isProducerVisible = this.franchise?.name != null
        )
    }

    fun Anime.mapToUserList() =
        AnimeUserListUI(
            itemId = ANIME_USER_LIST_INFO,
            addedCount = this.usersList.addedCount,
            completedCount = this.usersList.completedCount,
            watchingCount = this.usersList.watchingCount,
            droppedCount = this.usersList.droppedCount,
            generalCount = this.usersList.generalCount
        )


    fun Anime.mapToScreenshots(): AnimeScreenshotsUI? {
        return if (this.screenshots.isNotEmpty()) {
            AnimeScreenshotsUI(
                itemId = ANIME_SCREENSHOTS_INFO,
                list = this.screenshots
            )
        } else {
            null
        }
    }


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
        itemId = this._id + ANIME_INFO_PREFIX,
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
        private const val ANIME_DESCRIPTION_ID = "anime_description_id"
        private const val ANIME_INFO_PREFIX = "anime_info_prefix"
        private const val ANIME_PRODUCER_INFO = "anime_producer_info_prefix"
        private const val ANIME_SCREENSHOTS_INFO = "anime_screenshots_info_prefix"
        private const val ANIME_USER_LIST_INFO = "anime_user_list_info_prefix"




        private const val ANONS = "anons"
        private const val ONGOING = "ongoing"
        private const val RELEASED = "released"
        private const val DATE_FORMAT = "dd MMMM YYYY"
    }
}