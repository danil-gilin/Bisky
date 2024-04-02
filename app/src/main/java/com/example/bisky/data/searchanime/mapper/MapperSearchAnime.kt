package com.example.bisky.data.searchanime.mapper

import com.example.GetSearchAnimeQuery
import com.example.bisky.common.ext.toOneNumberAfterDot
import com.example.bisky.domain.repository.searchanime.model.AnimeSearch
import com.example.type.StatusEnum

fun GetSearchAnimeQuery.GetAnime.mapToDomain() = AnimeSearch(
    _id = this._id,
    label = this.labels.ru ?: this.labels.en,
    poster = this.poster,
    scores = this.score.averageScore.toOneNumberAfterDot(),
    status = if (this.status== StatusEnum.UNKNOWN__) null else this.status.name
)