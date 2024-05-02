package com.example.bisky.ui.screen.archivepage.watchedscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView.Event
import com.example.bisky.ui.screen.archivepage.watchedscreen.mapper.AnimeWatchedMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedScreenViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val animeWatchedMapper: AnimeWatchedMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(WatchedScreenView.State())
    val uiState: StateFlow<WatchedScreenView.State> = _uiState

    init {
        initData()
        subscribeAnimeCollection()
    }

    private fun subscribeAnimeCollection() = viewModelScope.launch {
        collectionRepository
            .subscribeUserCollectionAnime(CollectionAnime.COMPLETED)
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

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
            Event.OnRefresh -> initData()
        }
    }

    private fun initData() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }
        collectionRepository.getUserCollectionAnime(CollectionAnime.COMPLETED)
    }
}