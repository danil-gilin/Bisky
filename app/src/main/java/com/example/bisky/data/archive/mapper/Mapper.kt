package com.example.bisky.data.archive.mapper

import com.example.GetQuickSearchAnimeQuery
import com.example.GetUserCollectionAnimeQuery
import com.example.GetUserCollectionQuickSelectAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.searchanime.mapper.mapToAge
import com.example.bisky.data.searchanime.mapper.mapToDomain
import com.example.bisky.data.searchanime.mapper.mapToKindDomain
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.anime.model.Episodes
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime
import com.example.bisky.domain.repository.archive.model.AnimeQuickSelect
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.type.KindEnum
import com.example.type.ListStatusEnum
import com.example.type.StatusEnum


fun CollectionAnime.mapToStatusEnum() = when (this) {
    CollectionAnime.ADDED -> ListStatusEnum.added
    CollectionAnime.COMPLETED -> ListStatusEnum.completed
    CollectionAnime.DROPPED -> ListStatusEnum.dropped
    CollectionAnime.WATCHING -> ListStatusEnum.watching
    CollectionAnime.NONE -> ListStatusEnum.UNKNOWN__
}

fun GetUserCollectionAnimeQuery.AnimeEstimate.mapToDomain() = AnimeUserCollection(
    id = this.base._id,
    name = this.base.labels.ru ?: this.base.labels.en.orEmpty(),
    poster = this.base.poster.orEmpty()
)

fun List<AnimeUserCollection>.mapToAddCollection() = this.map {
    AddAnime(
        id = it.id,
        name = it.name,
        poster = it.poster
    )
}

fun List<AnimeUserCollection>.mapToWatchCollection() = this.map {
    WatchAnime(
        id = it.id,
        name = it.name,
        poster = it.poster
    )
}

fun List<AnimeUserCollection>.mapToCompleteCollection() = this.map {
    CompleteAnime(
        id = it.id,
        name = it.name,
        poster = it.poster
    )
}

fun List<WatchAnime>.mapWatchToAnimeUserCollection() = this.map {
    AnimeUserCollection(
        id = it.id,
        name = it.name,
        poster = it.poster
    )
}

fun List<CompleteAnime>.mapCompleteToAnimeUserCollection() = this.map {
    AnimeUserCollection(
        id = it.id,
        name = it.name,
        poster = it.poster
    )
}

fun List<AddAnime>.mapAddToAnimeUserCollection() = this.map {
    AnimeUserCollection(
        id = it.id,
        name = it.name,
        poster = it.poster
    )
}

fun GetUserCollectionQuickSelectAnimeQuery.AnimeEstimate.mapToDomain() = AnimeQuickSelect(
    id = this.base._id,
    label =  this.base.labels.ru ?: this.base.labels.en,
    poster = this.base.poster,
    episodes = this.mapEpisodesToDomain(),
    screenshots = this.base.screenshots,
    status = if (this.base.status == StatusEnum.UNKNOWN__) null else this.base.status.name,
    kind = this.mapToKindDomain(),
)

fun GetUserCollectionQuickSelectAnimeQuery.AnimeEstimate.mapToKindDomain() =
    when (this.base.kind) {
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

fun GetUserCollectionQuickSelectAnimeQuery.AnimeEstimate.mapEpisodesToDomain() = Episodes(
    averageDuration = this.base.episodes.averageDuration,
    count = this.base.episodes.count
)
