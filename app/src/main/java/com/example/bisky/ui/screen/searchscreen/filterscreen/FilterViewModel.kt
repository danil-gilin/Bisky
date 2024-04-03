package com.example.bisky.ui.screen.searchscreen.filterscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.model.GenreSimple
import com.example.bisky.ui.screen.searchscreen.filterscreen.FilterView.Event
import com.example.bisky.ui.screen.searchscreen.filterscreen.mapper.FilterMapper
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.StatusAnimeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val filterMapper: FilterMapper
): ViewModel() {
    private val _uiState = MutableStateFlow(FilterView.State())
    val uiState: StateFlow<FilterView.State> = _uiState

    var selectedGenreIds = listOf<String>()
    var listGenre = emptyList<GenreSimple>()

    init {
        viewModelScope.launch {
            requestAllGenre()
        }
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnStatusSelected -> onStatusSelected(event.status, event.isChecked)
            is Event.OnSortSelected -> onSortSelected(event.sort)
            is Event.OnOpenDialogDate -> _uiState.update { it.copy(isDateDialogShow = event.isShow) }
            is Event.OnYearSelected -> _uiState.update { it.copy(currentYear = event.year) }
            is Event.OnGenreSelected -> onGenreSelected(event.genreId, event.isAdd)
        }
    }

    private fun requestAllGenre() = viewModelScope.launch {
        genreRepository.getAllGenresWithSimpleInfo().onSuccess {
           listGenre = it
           val items = filterMapper.mapGenreToUI(it, selectedGenreIds)
            _uiState.update { it.copy(genreFilterUI = items) }
        }.onError {
            it
        }
    }

    private fun onGenreSelected(genreId: String, isAdd: Boolean) {
        selectedGenreIds = if (isAdd) {
            selectedGenreIds.plus(genreId)
        } else {
            selectedGenreIds.minus(genreId)
        }
        val items = filterMapper.mapGenreToUI(listGenre, selectedGenreIds).sortedBy { !it.isSelected }
        _uiState.update { it.copy(genreFilterUI = items) }
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