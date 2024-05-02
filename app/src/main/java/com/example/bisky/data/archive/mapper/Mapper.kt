package com.example.bisky.data.archive.mapper

import com.example.GetUserCollectionAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.type.ListStatusEnum


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
