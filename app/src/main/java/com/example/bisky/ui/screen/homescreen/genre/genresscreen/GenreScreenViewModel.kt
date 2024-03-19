package com.example.bisky.ui.screen.homescreen.genre.genresscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreenView.Event
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreenView.State
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.mapper.AnimeGenreMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreScreenViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val animeGenreMapper: AnimeGenreMapper,
    private val savedState: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState
    val id = savedState.get<String>("id").orEmpty()

    @Volatile
    var page = 1
    @Volatile
    var hasMore = true

    fun onEvent(event: Event) {
        when(event) {
            Event.OnGetMore -> onGetMore()
        }
    }

    init {
        initData()
    }

    private fun initData() = viewModelScope.launch {
        val name = savedState.get<String>("name").orEmpty()
        _uiState.update {
            it.copy(
                title = name
            )
        }
        getAnime()
    }
    private fun onGetMore() = viewModelScope.launch {
        getAnime()
    }

    suspend private fun getAnime() {
        if (uiState.value.isLoading || !hasMore) return
        _uiState.update {
            it.copy(isLoading = true)
        }
        genreRepository.getAnimesGenre(id, page).onSuccess { items ->
            if (items.isEmpty()) {
                hasMore = false
                _uiState.update {
                    it.copy(isLoading = false)
                }
                return
            }
            _uiState.update {
                val items = animeGenreMapper.mapToUI(items)
                it.copy(
                    isLoading = false,
                    items = it.items.plus(items)
                )
            }
            page++
        }.onError {
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}