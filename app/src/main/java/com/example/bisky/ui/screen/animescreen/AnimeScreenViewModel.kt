package com.example.bisky.ui.screen.animescreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.Event
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.State
import com.example.bisky.ui.screen.animescreen.mapper.AnimeFullInfoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeScreenViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val savedState: SavedStateHandle,
    private val animeFullInfoMapper: AnimeFullInfoMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    init {
        viewModelScope.launch {
            getAnimeInfo()
        }
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnClickFullDescription -> onClickFullDescription(event.isFullInfoDescription)
        }
    }

    private fun onClickFullDescription(fullInfoDescription: Boolean) {
        _uiState.update {
            it.copy(items = animeFullInfoMapper.updateDescriptionItem(it.items, fullInfoDescription))
        }
    }

    private suspend fun getAnimeInfo() {
        val id = savedState.get<String>("id") ?: return
        animeRepository.getAnime(id).onSuccess { anime ->
            if (anime == null) return
            _uiState.update {
                it.copy(items = animeFullInfoMapper.map(anime))
            }
        }.onError {
            it
        }
    }
}
