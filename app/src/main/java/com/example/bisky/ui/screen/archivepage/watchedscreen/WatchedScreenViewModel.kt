package com.example.bisky.ui.screen.archivepage.watchedscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView.Event
import com.example.bisky.ui.screen.archivepage.watchedscreen.mapper.AnimeWatchedMapper
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.TextSearchUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class WatchedScreenViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val animeWatchedMapper: AnimeWatchedMapper,
    private val searchUIMapper: TextSearchUIMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(WatchedScreenView.State())
    val uiState: StateFlow<WatchedScreenView.State> = _uiState

    init {
        initData()
        subscribeAnimeCollection()
    }

    private fun subscribeAnimeCollection() = viewModelScope.launch {
        val searchFlow = _uiState.value.quickSelectUI.searchTextField.textAsFlow()
        collectionRepository
            .subscribeUserCollectionAnime(CollectionAnime.COMPLETED)
            .combine(searchFlow){ anime , query ->
                updateTextSearchUi(query)
                anime.mapNotNull {
                    if (it.name.lowercase().contains(query.toString().lowercase())) {
                        it
                    } else {
                        null
                    }
                }
            }
            .collectLatest {
                val items = animeWatchedMapper.mapToUI(it)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        items = items
                    )
                }
            }
    }

    private fun updateTextSearchUi(query: TextFieldCharSequence) {
        val searchUI = searchUIMapper.map(query.toString())
        _uiState.update {
            it.copy(
                quickSelectUI = it.quickSelectUI.copy(searchUI = searchUI)
            )
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
            Event.OnRefresh -> initData()
            Event.OnSearchClick -> handleOnSearchClick()
        }
    }

    private fun handleOnSearchClick() {
        _uiState.update {
            it.copy(
                quickSelectUI = it.quickSelectUI.copy(isSearchVisible = !it.quickSelectUI.isSearchVisible)
            )
        }
    }

    private fun initData() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }
        collectionRepository.getUserCollectionAnime(CollectionAnime.COMPLETED)
    }
}