package com.example.bisky.data.newseries

import com.example.GetNewSeriesAnimeQuery
import com.example.bisky.domain.repository.newseries.model.NewSeriesAnime

fun GetNewSeriesAnimeQuery.GetAnime.mapToDomain() = NewSeriesAnime(
    id = _id,
    name = labels.ru ?: labels.en.orEmpty(),
    poster = poster.orEmpty(),
    series = episodes.airedCount
)