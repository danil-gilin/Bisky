package com.example.bisky.ui.screen.searchpage.filterscreen

import com.example.bisky.ui.screen.searchpage.filterscreen.model.GenreFilterUI
import com.example.bisky.ui.screen.searchpage.filterscreen.model.ScoreFilterUI
import com.example.bisky.ui.screen.searchpage.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchpage.filterscreen.model.StatusAnimeFilter
import java.time.LocalDate

interface FilterView {
    data class State(
        val selectedStatus: List<StatusAnimeFilter> = emptyList(),
        val selectedSort: SortAnimeFilter = SortAnimeFilter.RATING,
        val currentYear: Int = LocalDate.now().year,
        val genreFilterUI: List<GenreFilterUI> = emptyList(),
        val scoreRange: ScoreFilterUI = ScoreFilterUI(0f..10f,"Неважно"),
        val isDateDialogShow: Boolean = false
    )

    sealed class Event {
        data class OnStatusSelected(
            val status: StatusAnimeFilter,
            val isChecked: Boolean
        ) : Event()

        data class OnSortSelected(
            val sort: SortAnimeFilter,
        ) : Event()

        data class OnOpenDialogDate(
            val isShow: Boolean,
        ) : Event()
        data class OnScoreSelected(
            val scoreRange: ClosedFloatingPointRange<Float>,
        ) : Event()
        data class OnYearSelected(
            val year: Int,
        ) : Event()

        data class OnGenreSelected(
            val genreId: String,
            val isAdd: Boolean
        ) : Event()
        data object OnClearClick: Event()
    }

    sealed class Action {
        data object ShowBottomNav: Action()
        data object UpdateSearchAnime: Action()
    }
}