package com.example.bisky.ui.screen.searchpage.searchrootscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.search.SearchEventBus
import com.example.bisky.domain.eventbus.search.SearchEventBusEvent
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.ui.screen.searchpage.searchrootscreen.SearchView.Event
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.SearchAnimeMapper
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.TextSearchUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val textSearchUIMapper: TextSearchUIMapper,
    private val searchAnimeRepository: SearchAnimeRepository,
    private val searchAnimeMapper: SearchAnimeMapper,
    private val searchEventBus: SearchEventBus
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
                getAnime(null)
            }
        }
        subscribeSearchEvent()
    }

    @OptIn(FlowPreview::class)
    private suspend fun onSearchTextUpdate() {
        _uiState.value.searchTextField
            .textAsFlow()
            .distinctUntilChanged { old, new ->
                old.toString() == new.toString()
            }
            .map {
                val searchUI = textSearchUIMapper.map(it.toString())
                val isInputNotEmpty = it.isNotEmpty()
                _uiState.update {
                    it.copy(
                        searchUI = searchUI,
                        isLoading = isInputNotEmpty
                    )
                }
                it
            }
            .debounce(300L)
            .collectLatest {
                getAnime(it.toString())
            }
    }

    private suspend fun getAnime(input: String?) {
        val searchText = if (input.isNullOrEmpty()) null else input
        _uiState.update {
            it.copy(
                items = emptyList(),
                isLoading = true,
                isEmptyResult = false
            )
        }
        searchAnimeRepository.getAnimes(searchText).onSuccess {
                val items = searchAnimeMapper.mapToUI(it)
                _uiState.update {
                    it.copy(
                        items = items,
                        isLoading = false,
                        isEmptyResult = items.isEmpty()
                    )
                }

        }.onError {
            it
        }
    }

    private fun subscribeSearchEvent() = viewModelScope.launch {
        searchEventBus.eventsFlow.collectLatest { event ->
            when(event) {
                SearchEventBusEvent.SearchAnime -> getAnime(_uiState.value.searchTextField.text.toString())
            }
        }
    }
}