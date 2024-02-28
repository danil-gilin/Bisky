package com.example.bisky.data.seasonanime.mapper

import com.example.SeasonAnimeQuery
import com.example.bisky.data.seasonanime.remote.model.Genre
import com.example.bisky.data.seasonanime.remote.model.Name
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime

fun SeasonAnimeQuery.GetAnimePage.mapToDomain(): SeasonAnime =
    SeasonAnime(
        poster = poster,
        labels = labels,
        genres = this.genres.mapToDomain(),
        description = description,
        screenshots = screenshots,
        scores = scores,
    )

fun List<SeasonAnimeQuery.Genre>.mapToDomain() = map {
    Genre(
        name = it.name.mapToDomain()
    )
}

fun SeasonAnimeQuery.Name.mapToDomain() = Name(
    en = en,
    ru = ru
)
