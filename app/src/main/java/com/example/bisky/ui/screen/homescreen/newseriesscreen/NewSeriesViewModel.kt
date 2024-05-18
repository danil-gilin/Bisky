package com.example.bisky.ui.screen.homescreen.newseriesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.newseries.NewSeriesAnimeRepository
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesView.Event
import com.example.bisky.ui.screen.homescreen.newseriesscreen.mapper.NewSeriesMapper
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreenView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewSeriesViewModel @Inject constructor(
    private val newSeriesAnimeRepository: NewSeriesAnimeRepository,
    private val newSeriesMapper: NewSeriesMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewSeriesView.State())
    val uiState: StateFlow<NewSeriesView.State> = _uiState

    init {
        viewModelScope.launch {
            initData()
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
            Event.OnGetMore -> Unit
        }
    }

    private suspend fun initData() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        newSeriesAnimeRepository.getNewSeriesAnime().onSuccess {
            val item = newSeriesMapper.mapToUi(it)
            _uiState.update {
                it.copy(
                    itemsAnime = item
                )
            }
        }.onError {
            it
        }
        _uiState.update {
            it.copy(
                isLoading = false,
                isEmptyState = it.itemsAnime.isEmpty()
            )
        }
    }
}