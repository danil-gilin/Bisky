package com.example.bisky.ui.screen.searchscreen.filterscreen

import androidx.lifecycle.ViewModel
import com.example.bisky.ui.screen.searchscreen.filterscreen.FilterView.Event
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.StatusAnimeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(FilterView.State())
    val uiState: StateFlow<FilterView.State> = _uiState



    fun onEvent(event: Event) {
        when(event) {
            is Event.OnStatusSelected -> onStatusSelected(event.status, event.isChecked)
            is Event.OnSortSelected -> onSortSelected(event.sort)
            is Event.OnOpenDialogDate ->  _uiState.update { it.copy(isDateDialogShow = event.isShow) }
            is Event.OnYearSelected ->  _uiState.update { it.copy(currentYear = event.year) }
        }
    }

    private fun onStatusSelected(status: StatusAnimeFilter, checked: Boolean) {
        val currentStatus = uiState.value.selectedStatus
        val statusUpdated = if (checked) {
            currentStatus.plus(status)
        } else {
            currentStatus.minus(status)
        }
        _uiState.update { it.copy(selectedStatus = statusUpdated) }
    }

    private fun onSortSelected(sortAnimeFilter: SortAnimeFilter) {
        _uiState.update { it.copy(selectedSort = sortAnimeFilter) }
    }
}