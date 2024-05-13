package com.example.bisky.data.searchanime.mapper

import com.example.GetQuickSearchAnimeQuery
import com.example.GetSearchAnimeQuery
import com.example.bisky.common.ext.toOneNumberAfterDot
import com.example.bisky.domain.repository.anime.model.Episodes
import com.example.bisky.domain.repository.anime.model.Franchise
import com.example.bisky.domain.repository.anime.model.Score
import com.example.bisky.domain.repository.anime.model.Studio
import com.example.bisky.domain.repository.searchanime.model.AnimeQuickSearch
import com.example.bisky.domain.repository.searchanime.model.AnimeSearch
import com.example.type.KindEnum
import com.example.type.RatingEnum
import com.example.type.StatusEnum

fun GetSearchAnimeQuery.GetAnime.mapToDomain() = AnimeSearch(
    _id = this._id,
    label = this.labels.ru ?: this.labels.en,
    poster = this.poster,
    scores = this.score.averageScore.toOneNumberAfterDot(),
    status = if (this.status == StatusEnum.UNKNOWN__) null else this.status.name
)

fun GetQuickSearchAnimeQuery.GetAnime.mapToDomain() = AnimeQuickSearch(
    _id = this._id,
    label = this.labels.ru ?: this.labels.en,
    dates = this.dates.airedOn?.toString(),
    description = this.description.ru ?: this.description.en,
    genres = this.genres.mapNotNull { it.mapToDomain() },
    score = this.score.mapToDomain(),
    poster = this.poster,
    episodes = this.episodes.mapToDomain(),
    franchise = this.franchise?.mapToDomain(),
    screenshots = this.screenshots,
    studios = this.studios.mapToDomain(),
    status = if (this.status == StatusEnum.UNKNOWN__) null else this.status.name,
    kind = this.mapToKindDomain(),
    age = this.mapToAge(),
)

fun GetQuickSearchAnimeQuery.Genre.mapToDomain() = this.name.ru ?: this.name.en


fun List<GetQuickSearchAnimeQuery.Studio>.mapToDomain() = this.map {
    Studio(
        name = it.name,
        logo = it.logo
    )
}

fun GetQuickSearchAnimeQuery.Score.mapToDomain() = Score(
    averageScore = averageScore.toOneNumberAfterDot(),
    count = count
)

fun GetQuickSearchAnimeQuery.Episodes.mapToDomain() = Episodes(
    averageDuration = duration ?: 0,
    count = count
)

fun GetQuickSearchAnimeQuery.Franchise.mapToDomain() = Franchise(
    _id = _id,
    name = this.name.ru ?: this.name.en
)

fun GetQuickSearchAnimeQuery.GetAnime.mapToKindDomain() =
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

fun GetQuickSearchAnimeQuery.GetAnime.mapToAge() =
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