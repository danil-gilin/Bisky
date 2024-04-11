package com.example.bisky.ui.screen.archivepage.watchsreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.ArchiveRepository
import com.example.bisky.ui.screen.archivepage.container.ArchiveContainerView
import com.example.bisky.ui.screen.archivepage.watchsreen.WatchScreenView.Event
import com.example.bisky.ui.screen.archivepage.watchsreen.mapper.AnimeWatchMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchScreenViewModel @Inject constructor(
    private val archiveRepository: ArchiveRepository,
    private val animeWatchMapper: AnimeWatchMapper
): ViewModel() {
    private val _uiState = MutableStateFlow(WatchScreenView.State())
    val uiState: StateFlow<WatchScreenView.State> = _uiState

    init {
        initData()
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
        }
    }

    private fun initData() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }
        archiveRepository.getUserCollectionAnime(CollectionAnime.WATCHING).onSuccess {
            val items = animeWatchMapper.mapToUI(it)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    items = items
                )
            }
        }.onError {
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}