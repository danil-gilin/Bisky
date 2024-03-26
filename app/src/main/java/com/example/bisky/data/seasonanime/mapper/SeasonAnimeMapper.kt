package com.example.bisky.data.seasonanime.mapper

import com.example.SeasonAnimeQuery
import com.example.bisky.common.ext.toOneNumberAfterDot
import com.example.bisky.data.seasonanime.local.model.GenreEntity
import com.example.bisky.data.seasonanime.local.model.NameEntity
import com.example.bisky.data.seasonanime.local.model.SeasonAnimeEntity
import com.example.bisky.domain.repository.seasonanime.model.Genre
import com.example.bisky.domain.repository.seasonanime.model.Name
import com.example.bisky.domain.repository.seasonanime.model.SeasonAnime

fun SeasonAnimeEntity.mapToDomain(): SeasonAnime =
    SeasonAnime(
        _id = _id,
        poster = poster,
        labels = labels,
        genres = this.genres?.mapToDomain().orEmpty(),
        description = description,
        screenshots = screenshots,
        scores = scores
    )

fun List<GenreEntity>.mapToDomain() = map {
    Genre(
        name = it.name.mapToDomain()
    )
}

fun NameEntity.mapToDomain() = Name(
    en = en,
    ru = ru
)

fun SeasonAnimeQuery.GetAnime.mapToEntity(): SeasonAnimeEntity =
    SeasonAnimeEntity(
        _id = _id,
        poster = poster,
        labels = labels.ru ?: labels.en,
        genres = this.genres.mapToEntity(),
        description = description.ru ?: description.en,
        screenshots = screenshots,
        scores = score.averageScore.toOneNumberAfterDot()
    )

fun List<SeasonAnimeQuery.Genre>.mapToEntity() = map {
    GenreEntity(
        name = it.name.mapToEntity()
    )
}

fun SeasonAnimeQuery.Name.mapToEntity() = NameEntity(
    en = en.orEmpty(),
    ru = ru.orEmpty()
)
