package com.example.bisky.ui.screen.animescreen.mapper

import com.example.bisky.R
import com.example.bisky.common.model.BaseItem
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.anime.model.SimilarAnime
import com.example.bisky.ui.screen.animescreen.model.body.AnimeDescriptionUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeProducerInfoUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeRatingUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeScreenshotsUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeUserListUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeVideoUI
import com.example.bisky.ui.screen.animescreen.model.body.SimilarAnimeListUI
import com.example.bisky.ui.screen.animescreen.model.body.SimilarAnimeUI
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

        val ratingInfo = anime.mapToRatingInfo()
        listItems.add(ratingInfo)

        val userlistInfo = anime.mapToUserList()
        listItems.add(userlistInfo)

        val similarAnimeInfo = anime.mapToSimilarAnime()
        similarAnimeInfo?.let { listItems.add(it) }

        val screenshotInfo = anime.mapToScreenshots()
        screenshotInfo?.let { listItems.add(it) }

        val videoInfo = anime.mapToVideo()
        videoInfo?.let { listItems.add(it) }

        return listItems
    }

    fun updateDescriptionItem(
        item: List<BaseItem>,
        isFullInfo: Boolean
    ): List<BaseItem> {
        val descriptionUi =
            item.find { it.itemId == ANIME_DESCRIPTION_ID } as? AnimeDescriptionUI ?: return item
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
            producer = this.studios.map { it.name }.joinToString(", "),
            isProducerVisible = this.studios.isNotEmpty()
        )
    }

    fun Anime.mapToRatingInfo(): AnimeRatingUI {
        return AnimeRatingUI(
            itemId = ANIME_RATING_INFO,
            rating = this.score.averageScore.toString(),
            ratingColor = this.score.averageScore.mapToScoreColor(),
            isRatingVisible = this.score.averageScore > 0.0,
            ratingCount = "${this.score.count} оценок",
            ratingUser = userData.score.toString(),
            ratingColorUser = userData.score?.toDouble()?.mapToScoreColor() ?: R.color.light_400,
            isRatingVisibleUser = userData.score != null,
            isRatingEnabled = userData.collection != CollectionAnime.NONE,
            selectedScore = -1
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

    fun Anime.mapToVideo(): AnimeVideoUI? {
        return if (this.videos.isNotEmpty()) {
            AnimeVideoUI(
                itemId = ANIME_VIDEO_INFO,
                list = this.videos.map { it.url }
            )
        } else {
            null
        }
    }

    fun Anime.mapToSimilarAnime(): SimilarAnimeListUI? {
        if (this.similarAnime.isEmpty()) return null
        return SimilarAnimeListUI(
            itemId = ANIME_SIMILAR_ANIME_LIST_INFO,
            items = this.similarAnime.mapToUI()
        )
    }

    fun List<SimilarAnime>.mapToUI() = this.map {
        SimilarAnimeUI(
            itemId = it.id,
            name = it.name.orEmpty(),
            poster = it.poster ?: R.drawable.ic_logo,
            rating = it.rating.toString(),
            ratingColor = it.rating.mapToScoreColor(),
            isRatingVisible = it.rating > 0.0
        )
    }


    fun Anime.mapToAnimeCard() = AnimeCardFullInfoUI(
        itemId = this._id + ANIME_CARD_PREFIX,
        poster = this.poster ?: R.drawable.ic_logo,
        score = this.score.averageScore.toString(),
        age = age,
        ageVisible = true,
        isScoreVisible = this.score.averageScore > 0.0,
        scoreColor = this.score.averageScore.mapToScoreColor()
    )


    fun Double.mapToScoreColor() = when (this) {
        in 0.0..4.9 -> R.color.red
        in 5.0..7.8 -> R.color.gray
        else -> R.color.lime
    }

    fun Anime.mapToAnimeInfo() = InfoAnimeItemUI(
        itemId = this._id + ANIME_INFO_PREFIX,
        imgCollectionAdded = mapToCollectionIcon(userData.collection, CollectionAnime.ADDED),
        imgCollectionCompleted = mapToCollectionIcon(userData.collection, CollectionAnime.COMPLETED),
        imgCollectionDropped = mapToCollectionIcon(userData.collection, CollectionAnime.DROPPED),
        imgCollectionWatching = mapToCollectionIcon(userData.collection, CollectionAnime.WATCHING),
        imgCollectionNone = mapToCollectionIcon(userData.collection, CollectionAnime.NONE),
        statusColor = this.mapToStatusColor(),
        infoStatus = this.mapToStatus(),
        infoDate = this.dates.formatDate(),
        infoDuration = this.mapToInfoDuration(),
        infoType = "${this.kind},"
    )

    private fun mapToCollectionIcon(collectionSelect: CollectionAnime, collectionType: CollectionAnime) =
        when (collectionType) {
            CollectionAnime.ADDED -> if (collectionSelect == collectionType)
                R.drawable.ic_added_collection else
                R.drawable.ic_added_collection_disable

            CollectionAnime.COMPLETED -> if (collectionSelect == collectionType)
                R.drawable.ic_completed_collection else
                R.drawable.ic_completed_collection_disable

            CollectionAnime.DROPPED -> if (collectionSelect == collectionType)
                R.drawable.ic_delete_collection else
                R.drawable.ic_delete_collection_disable

            CollectionAnime.WATCHING -> if (collectionSelect == collectionType)
                R.drawable.ic_play_collection else
                R.drawable.ic_play_collection_disable

            CollectionAnime.NONE -> if (collectionSelect == collectionType)
                R.drawable.ic_none_collection else
                R.drawable.ic_none_collection_disable
        }

    private fun Anime?.mapToInfoDuration(): String {
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

    private fun Anime.mapToStatus() = when (status) {
        RELEASED -> "вышел"
        ONGOING -> "идет"
        ANONS -> "анонс"
        else -> "Неизвестно"
    }

    private fun Anime.mapToStatusColor() = when (status) {
        RELEASED -> R.color.green
        ONGOING -> R.color.blue
        ANONS -> R.color.orange
        else -> R.color.gray
    }

    fun updateRatingScore(
        items: List<BaseItem>,
        selectedScore: Int
    ): List<BaseItem> {
        val descriptionUi =
            items.find { it.itemId == ANIME_RATING_INFO } as? AnimeRatingUI ?: return items
        return items.map {
            if (it.itemId == ANIME_RATING_INFO) {
                descriptionUi.copy(
                    selectedScore = selectedScore,
                    isRatingVisibleUser = false
                )
            } else {
                it
            }
        }
    }


    companion object {
        const val ANIME_CARD_PREFIX = "anime_card_prefix"
        const val ANIME_DESCRIPTION_ID = "anime_description_id"
        const val ANIME_INFO_PREFIX = "anime_info_prefix"
        const val ANIME_PRODUCER_INFO = "anime_producer_info_prefix"
        const val ANIME_SCREENSHOTS_INFO = "anime_screenshots_info_prefix"
        const val ANIME_VIDEO_INFO = "anime_video_info_prefix"
        const val ANIME_USER_LIST_INFO = "anime_user_list_info_prefix"
        const val ANIME_SIMILAR_ANIME_LIST_INFO = "anime_similar_anime_list_info_prefix"
        const val ANIME_RATING_INFO = "anime_rating_info_prefix"


        private const val ANONS = "anons"
        private const val ONGOING = "ongoing"
        private const val RELEASED = "released"
        private const val DATE_FORMAT = "dd MMMM YYYY"
    }
}