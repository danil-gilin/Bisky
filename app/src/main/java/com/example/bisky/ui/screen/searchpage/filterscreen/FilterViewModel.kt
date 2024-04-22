package com.example.bisky.ui.screen.searchpage.filterscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.domain.eventbus.search.SearchEventBus
import com.example.bisky.domain.eventbus.search.SearchEventBusEvent
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.model.GenreSimple
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.ui.screen.searchpage.filterscreen.FilterView.Action
import com.example.bisky.ui.screen.searchpage.filterscreen.FilterView.Event
import com.example.bisky.ui.screen.searchpage.filterscreen.mapper.FilterMapper
import com.example.bisky.ui.screen.searchpage.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchpage.filterscreen.model.StatusAnimeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val searchAnimeRepository: SearchAnimeRepository,
    private val filterMapper: FilterMapper,
    private val navigationEventBus: NavigationEventBus,
    private val searchEventBus: SearchEventBus
): ViewModel() {
    private val _uiState = MutableStateFlow(FilterView.State())
    val uiState: StateFlow<FilterView.State> = _uiState

    var listGenre = emptyList<GenreSimple>()

    init {
        viewModelScope.launch {
            navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(false))
            requestAllGenre()
            updateFilter()
        }
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnStatusSelected -> onStatusSelected(event.status, event.isChecked)
            is Event.OnSortSelected -> onSortSelected(event.sort)
            is Event.OnOpenDialogDate -> _uiState.update { it.copy(isDateDialogShow = event.isShow) }
            is Event.OnYearSelected -> onYearSelected(event.year)
            is Event.OnGenreSelected -> onGenreSelected(event.genreId, event.isAdd)
            is Event.OnScoreSelected -> onScoreSelected(event.scoreRange)
            Event.OnClearClick -> onClearClick()
        }
    }

    fun onAction(action: Action) {
        when(action) {
            Action.ShowBottomNav -> navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(true))
            Action.UpdateSearchAnime -> searchEventBus.emitEvent(SearchEventBusEvent.SearchAnime)
        }
    }

    private fun onClearClick() {
        searchAnimeRepository.clearSearchFilter()
        updateFilter()
    }

    private fun onYearSelected(year: Int) {
        val filter = searchAnimeRepository.fetchSearchFilter()
        searchAnimeRepository.updateSearchFilter(filter.copy(year = year))
        updateFilter()
    }
    private fun onScoreSelected(scoreRange: ClosedFloatingPointRange<Float>) {
        val filter = searchAnimeRepository.fetchSearchFilter()
        searchAnimeRepository.updateSearchFilter(filter.copy(scoreRange =scoreRange))
        updateFilter()
    }

    private fun requestAllGenre() = viewModelScope.launch {
        genreRepository.getAllGenresWithSimpleInfo().onSuccess {
           listGenre = it
            val filter = searchAnimeRepository.fetchSearchFilter()
           val items = filterMapper.mapGenreToUI(it, filter.genres.orEmpty()).sortedBy { !it.isSelected }
            _uiState.update { it.copy(genreFilterUI = items) }
        }.onError {
            it
        }
    }

    private fun updateFilter() = viewModelScope.launch {
       val filter = searchAnimeRepository.fetchSearchFilter()
        _uiState.update {
            it.copy(
                selectedStatus = filter.status.orEmpty(),
                selectedSort = filter.sorted ?: SortAnimeFilter.RATING,
                currentYear = filter.year,
                genreFilterUI = filterMapper.mapGenreToUI(listGenre, filter.genres.orEmpty()).sortedBy { !it.isSelected },
                scoreRange = filterMapper.mapScoreToUI(scoreRange = filter.scoreRange)
            )
        }
    }

    private fun onGenreSelected(genreId: String, isAdd: Boolean) {
        val filter = searchAnimeRepository.fetchSearchFilter()
        val selectedGenreIds = if (isAdd) {
            filter.genres.orEmpty().plus(genreId)
        } else {
            filter.genres.orEmpty().minus(genreId)
        }
        searchAnimeRepository.updateSearchFilter(filter.copy(genres = selectedGenreIds.ifEmpty { null }))
        updateFilter()
    }

    private fun onStatusSelected(status: StatusAnimeFilter, checked: Boolean) {
        val filter = searchAnimeRepository.fetchSearchFilter()
        val currentStatus = uiState.value.selectedStatus
        val statusUpdated = if (checked) {
            currentStatus.plus(status)
        } else {
            currentStatus.minus(status)
        }
        searchAnimeRepository.updateSearchFilter(filter.copy(status = statusUpdated.ifEmpty { null }))
        updateFilter()
    }

    private fun onSortSelected(sortAnimeFilter: SortAnimeFilter) {
        val filter = searchAnimeRepository.fetchSearchFilter()
        searchAnimeRepository.updateSearchFilter(filter.copy(sorted =sortAnimeFilter))
        updateFilter()
    }
}