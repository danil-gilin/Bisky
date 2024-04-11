package com.example.bisky.data.archive.mapper

import com.example.GetUserCollectionAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection
import com.example.type.ListStatusEnum


fun CollectionAnime.mapToStatusEnum() = when(this) {
    CollectionAnime.ADDED -> ListStatusEnum.added
    CollectionAnime.COMPLETED -> ListStatusEnum.completed
    CollectionAnime.DROPPED ->ListStatusEnum.dropped
    CollectionAnime.WATCHING -> ListStatusEnum.watching
    CollectionAnime.NONE -> ListStatusEnum.UNKNOWN__
}

fun GetUserCollectionAnimeQuery.AnimeEstimate.mapToDomain() = AnimeUserCollection(
    id = this.base._id,
    name = this.base.labels.ru ?: this.base.labels.en.orEmpty(),
    poster = this.base.poster.orEmpty()
)
