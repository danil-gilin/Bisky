package com.example.bisky.ui.screen.searchscreen.filterscreen.mapper

import com.example.bisky.domain.repository.genre.model.GenreSimple
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.GenreFilterUI
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.ScoreFilterUI
import javax.inject.Inject
import kotlin.math.roundToInt

class FilterMapper @Inject constructor() {

    fun mapGenreToUI(
        listGenre: List<GenreSimple>,
        selectedGenreId: List<String>
    ) = listGenre.mapNotNull {
        if (it.name.isNullOrEmpty()) return@mapNotNull null
        GenreFilterUI(
            id = it.id,
            name = it.name,
            isSelected = selectedGenreId.contains(it.id)
        )
    }

    fun mapScoreToUI(scoreRange: ClosedFloatingPointRange<Float>): ScoreFilterUI {
        val fixedScoreRange = fixScoreRange(scoreRange)
        return ScoreFilterUI(
            scoreRange = fixedScoreRange,
            text = mapToScoreText(fixedScoreRange)
        )
    }

    private fun fixScoreRange(
        scoreRange: ClosedFloatingPointRange<Float>
    ): ClosedFloatingPointRange<Float> {
        val start = scoreRange.start.roundToInt().toFloat()
        val end = scoreRange.endInclusive.roundToInt().toFloat()
        return start..end
    }

    private fun mapToScoreText(scoreRange: ClosedFloatingPointRange<Float>): String {
        if (scoreRange == 0f..10f)
            return "Неважно"
        if (scoreRange.start == 0f)
            return "до ${scoreRange.endInclusive.toInt()}"
        if (scoreRange.endInclusive == 10f)
            return "от ${scoreRange.start.toInt()}"
        return "от ${scoreRange.start.toInt()} до ${scoreRange.endInclusive.toInt()}"
    }
}