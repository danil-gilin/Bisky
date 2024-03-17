package com.example.bisky.data.seasonanime.mapper

import com.example.SeasonAnimeQuery
import com.example.bisky.data.seasonanime.remote.model.Genre
import com.example.bisky.data.seasonanime.remote.model.Name
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime

fun SeasonAnimeQuery.GetAnime.mapToDomain(): SeasonAnime =
    SeasonAnime(
        poster = poster,
        labels = labels.ru ?: labels.en,
        genres = this.genres.mapToDomain(),
        description = description.ru ?: description.en,
        screenshots = screenshots,
        scores = score.averageScore
    )

fun List<SeasonAnimeQuery.Genre>.mapToDomain() = map {
    Genre(
        name = it.name.mapToDomain()
    )
}

fun SeasonAnimeQuery.Name.mapToDomain() = Name(
    en = en.orEmpty(),
    ru = ru.orEmpty()
)
