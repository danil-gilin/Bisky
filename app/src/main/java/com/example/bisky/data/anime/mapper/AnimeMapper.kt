package com.example.bisky.data.anime.mapper

import com.example.GetAnimeQuery
import com.example.bisky.common.ext.toOneNumberAfterDot
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.domain.repository.anime.model.Episodes
import com.example.bisky.domain.repository.anime.model.Franchise
import com.example.bisky.domain.repository.anime.model.Collection
import com.example.bisky.domain.repository.anime.model.Score
import com.example.bisky.domain.repository.anime.model.SimilarAnime
import com.example.bisky.domain.repository.anime.model.Studio
import com.example.bisky.domain.repository.anime.model.UserData
import com.example.bisky.domain.repository.anime.model.UsersList
import com.example.bisky.domain.repository.anime.model.Video
import com.example.type.KindEnum
import com.example.type.ListStatusEnum
import com.example.type.RatingEnum
import com.example.type.StatusEnum

fun GetAnimeQuery.GetAnime.mapToDomain() = Anime(
    _id = this._id,
    label = this.labels.ru ?: this.labels.en,
    dates = this.dates.airedOn?.toString(),
    description = this.description.ru ?: this.description.en,
    genres = this.genres.mapNotNull { it.mapToDomain() },
    videos = this.videos.map { it.mapToDomain() },
    usersList = this.usersList.mapToDomain(),
    score = this.score.mapToDomain(),
    poster = this.poster,
    episodes = this.episodes.mapToDomain(),
    franchise = this.franchise?.mapToDomain(),
    screenshots = this.screenshots,
    studios = this.studios.mapToDomain(),
    status = if (this.status == StatusEnum.UNKNOWN__) null else this.status.name,
    kind = this.mapToKindDomain(),
    age = this.mapToAge(),
    similarAnime = this.related.map { it.mapToDomain() },
    userData = this.userData.mapToDomain()
)

fun GetAnimeQuery.Related.mapToDomain() = SimilarAnime(
    id = this.base._id,
    name = this.base.labels.ru ?: this.base.labels.en,
    poster = this.base.poster,
    rating = this.base.score.averageScore,
)

fun GetAnimeQuery.Genre.mapToDomain() = this.name.ru ?: this.name.en

fun GetAnimeQuery.Video.mapToDomain() =
    Video(
        name = this.name,
        url = this.url
    )

fun GetAnimeQuery.UserData.mapToDomain() = UserData(
    score = this.score,
    watchedSeries = this.watchedSeries,
    collection = this.mapToStatus()
)

fun GetAnimeQuery.UserData.mapToStatus() = when(animeStatus) {
    ListStatusEnum.added -> Collection.ADDED
    ListStatusEnum.completed -> Collection.COMPLETED
    ListStatusEnum.dropped -> Collection.DROPPED
    ListStatusEnum.watching -> Collection.WATCHING
    ListStatusEnum.UNKNOWN__ -> Collection.NONE
    null -> Collection.NONE
}


fun List<GetAnimeQuery.Studio>.mapToDomain() = this.map {
    Studio(
        name = it.name,
        logo = it.logo
    )
}

fun GetAnimeQuery.UsersList.mapToDomain() = UsersList(
    addedCount = addedCount,
    completedCount = completedCount,
    droppedCount = droppedCount,
    generalCount = generalCount,
    watchingCount = watchingCount
)

fun GetAnimeQuery.Score.mapToDomain() = Score(
    averageScore = averageScore.toOneNumberAfterDot(),
    count = count
)

fun GetAnimeQuery.Episodes.mapToDomain() = Episodes(
    averageDuration = averageDuration,
    count = count
)

fun GetAnimeQuery.Franchise.mapToDomain() = Franchise(
    _id = _id,
    name = this.name.ru ?: this.name.en
)

fun GetAnimeQuery.GetAnime.mapToKindDomain() =
    when (this.kind) {
        KindEnum.movie -> "Фильм"
        KindEnum.music -> "Музыка"
        KindEnum.none -> "Фильм"
        KindEnum.ona -> "ONA"
        KindEnum.ova -> "OVA"
        KindEnum.special -> "Спецвыпуск"
        KindEnum.tv -> "Сериал"
        KindEnum.tv_special -> "Сериал"
        KindEnum.UNKNOWN__ -> "Неизвестно"
        KindEnum.cm -> "Промо"
        KindEnum.pv -> "Промо"
    }

fun GetAnimeQuery.GetAnime.mapToAge() =
    when (this.rating) {
        RatingEnum.g -> "0+"
        RatingEnum.none -> "0+"
        RatingEnum.pg -> "6+"
        RatingEnum.pg_13 -> "14+"
        RatingEnum.r -> "16+"
        RatingEnum.r_plus -> "17+"
        RatingEnum.rx -> "18+"
        RatingEnum.UNKNOWN__ -> "0+"
    }

