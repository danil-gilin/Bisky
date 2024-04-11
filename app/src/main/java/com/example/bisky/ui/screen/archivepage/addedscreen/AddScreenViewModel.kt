package com.example.bisky.ui.screen.archivepage.addedscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.ArchiveRepository
import com.example.bisky.ui.screen.archivepage.addedscreen.AddScreenView.Event
import com.example.bisky.ui.screen.archivepage.addedscreen.mapper.AnimeAddMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val archiveRepository: ArchiveRepository,
    private val animeAddMapper: AnimeAddMapper
): ViewModel() {
    private val _uiState = MutableStateFlow(AddScreenView.State())
    val uiState: StateFlow<AddScreenView.State> = _uiState

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
        archiveRepository.getUserCollectionAnime(CollectionAnime.ADDED).onSuccess {
            val items = animeAddMapper.mapToUI(it)
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