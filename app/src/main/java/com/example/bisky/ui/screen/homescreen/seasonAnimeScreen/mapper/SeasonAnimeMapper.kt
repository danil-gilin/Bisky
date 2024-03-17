package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.mapper

import com.example.bisky.R
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.AnimeSeasonUI
import javax.inject.Inject

class SeasonAnimeMapper @Inject constructor() {

    fun map(items: List<SeasonAnime>): List<AnimeSeasonUI> {
        return items.map {
            AnimeSeasonUI(
                img = it.poster.orEmpty(),
                title = it.labels.orEmpty(),
                description = it.description.orEmpty(),
                rating = it.scores.toString(),
                isRatingVisible = it.scores > 0.0,
                ratingColor = it.mapToRatingColor(),
                genre = it.mapToGenre(),
                backgroundImg = it.screenshots.firstOrNull() ?: it.poster.orEmpty()
            )
        }
    }

    fun SeasonAnime.mapToGenre() = genres.map { it.name.ru }.joinToString(" | ")
    fun SeasonAnime.mapToRatingColor() = when (scores) {
        in 0.0..4.9 -> R.color.red
        in 5.0..7.8 -> R.color.gray
        else -> R.color.lime
    }
}
