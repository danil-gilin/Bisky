package com.example.bisky.domain.repository.searchanime.model

import com.example.bisky.ui.screen.searchpage.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchpage.filterscreen.model.StatusAnimeFilter
import java.time.LocalDate

data class FilterSearch(
    val year: Int = LocalDate.now().year,
    val scoreRange: ClosedFloatingPointRange<Float> = 0f..10f,
    val sorted: SortAnimeFilter? = null,
    val status: List<StatusAnimeFilter>? = null,
    val genres: List<String>? = null
)
