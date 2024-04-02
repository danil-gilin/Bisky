package com.example.bisky.ui.screen.searchscreen.filterscreen

import com.example.bisky.ui.screen.searchscreen.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.StatusAnimeFilter

interface FilterView {
    data class State(
        val selectedStatus: List<StatusAnimeFilter> = emptyList(),
        val selectedSort: SortAnimeFilter = SortAnimeFilter.RATING
    )

    sealed class Event {
        data class OnStatusSelected(
            val status: StatusAnimeFilter,
            val isChecked: Boolean
        ) : Event()

        data class OnSortSelected(
            val sort: SortAnimeFilter,
        ) : Event()
    }
}