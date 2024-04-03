package com.example.bisky.ui.screen.searchscreen.filterscreen

import com.example.bisky.ui.screen.searchscreen.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.StatusAnimeFilter
import java.time.LocalDate

interface FilterView {
    data class State(
        val selectedStatus: List<StatusAnimeFilter> = emptyList(),
        val selectedSort: SortAnimeFilter = SortAnimeFilter.RATING,
        val currentYear: Int = LocalDate.now().year,
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

        data class OnYearSelected(
            val year: Int,
        ) : Event()
    }
}