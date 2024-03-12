package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.mapper

import com.example.bisky.R
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.AnimeSeasonUI
import javax.inject.Inject

const val SHKIMORI_URL = "https://shikimori.one"
const val POSTER_URL = "/system/animes/original/"

class SeasonAnimeMapper @Inject constructor() {

    fun map(items: List<SeasonAnime>): List<AnimeSeasonUI> {
        return items.map {
            AnimeSeasonUI(
                img = it.mapToPoster().orEmpty(),
                title = it.labels.firstOrNull().orEmpty(),
                description = it.description.orEmpty(),
                rating = it.scores.toString(),
                isRatingVisible = it.scores > 0.0,
                ratingColor = it.mapToRatingColor(),
                genre = it.mapToGenre(),
                backgroundImg = it.screenshots.firstOrNull() ?: it.mapToPoster().orEmpty()
            )
        }
    }

    fun SeasonAnime.mapToGenre() = genres.map { it.name.ru }.joinToString(" | ")
    fun SeasonAnime.mapToRatingColor() = when (scores) {
        in 0.0..2.9 -> R.color.red
        in 3.0..4.0 -> R.color.gray
        else -> R.color.lime
    }

    fun SeasonAnime.mapToPoster() =
        poster?.let {
            "$SHKIMORI_URL$POSTER_URL$it.jpg"
        }
}
