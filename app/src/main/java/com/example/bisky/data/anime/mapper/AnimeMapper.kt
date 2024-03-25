package com.example.bisky.data.anime.mapper

import com.example.GetAnimeQuery
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.domain.repository.anime.model.Episodes
import com.example.bisky.domain.repository.anime.model.Franchise
import com.example.bisky.domain.repository.anime.model.Score
import com.example.bisky.domain.repository.anime.model.Studio
import com.example.bisky.domain.repository.anime.model.UsersList
import com.example.bisky.domain.repository.anime.model.Video
import com.example.type.KindEnum
import com.example.type.StatusEnum

fun GetAnimeQuery.GetAnime.mapToDomain() = Anime(
    _id = this._id,
    label = this.labels.ru ?: this.labels.en,
    dates = this.dates.releasedOn?.toString(),
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
    kind = this.mapToKindDomain()
)

fun GetAnimeQuery.Genre.mapToDomain() = this.name.ru ?: this.name.en

fun GetAnimeQuery.Video.mapToDomain() =
    Video(
        name = this.name,
        url = this.url
    )


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
    averageScore = averageScore,
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
        KindEnum.ona -> "Фильм"
        KindEnum.ova -> "Фильм"
        KindEnum.special -> "Спецвыпуск"
        KindEnum.tv -> "Сериал"
        KindEnum.tv_special -> "Сериал"
        KindEnum.UNKNOWN__ -> "Неизвестно"
    }

