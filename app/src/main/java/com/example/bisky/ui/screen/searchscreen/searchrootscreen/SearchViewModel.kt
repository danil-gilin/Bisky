package com.example.bisky.ui.screen.searchscreen.searchrootscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.ui.screen.searchscreen.searchrootscreen.SearchView.Event
import com.example.bisky.ui.screen.searchscreen.searchrootscreen.mapper.SearchAnimeMapper
import com.example.bisky.ui.screen.searchscreen.searchrootscreen.mapper.TextSearchUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val textSearchUIMapper: TextSearchUIMapper,
    private val searchAnimeRepository: SearchAnimeRepository,
    private val searchAnimeMapper: SearchAnimeMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchView.State())
    val uiState: StateFlow<SearchView.State> = _uiState
    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
            Event.OnGetMore -> Unit
            Event.OnSearchClick -> _uiState.update { it.copy(isSearchInputVisible = !it.isSearchInputVisible) }
        }
    }

    init {
        viewModelScope.launch {
            launch {
                onSearchTextUpdate()
            }
        }
    }

    @OptIn(FlowPreview::class)
    private suspend fun onSearchTextUpdate() {
        _uiState.value.searchTextField
            .textAsFlow()
            .map {
                val searchUI =
                    textSearchUIMapper.map(it.toString())
                _uiState.update { it.copy(searchUI = searchUI) }
                it
            }
            .debounce(300L)
            .collectLatest {
                getAnime(it.toString())
            }
    }

    private suspend fun getAnime(input: String) {
        searchAnimeRepository.getAnimes(input).onSuccess {
            if (input.isEmpty()) {
                _uiState.update { it.copy(items = emptyList()) }
            } else {
                val items = searchAnimeMapper.mapToUI(it)
                _uiState.update { it.copy(items = items) }
            }
        }.onError {
            it
        }
    }
}