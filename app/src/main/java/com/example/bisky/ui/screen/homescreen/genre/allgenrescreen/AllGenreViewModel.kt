package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.common.model.BaseItem
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.AllGenreView.Event
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.mapper.MapperAllGenre
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.model.LoaderUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllGenreViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val mapperAllGenre: MapperAllGenre
) : ViewModel() {
    private val _uiState = MutableStateFlow(AllGenreView.State())
    val uiState: StateFlow<AllGenreView.State> = _uiState

    @Volatile
    var page: Int = 1

    @Volatile
    var hasMore: Boolean = true

    init {
        getGenres()
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnScrollItem -> onScrollItem(event.position)
        }
    }

    fun onScrollItem(position: Int) {
        if (position > uiState.value.items.size - 8  && hasMore) {
            getGenres()
        }
        _uiState.update { it.copy(positionScroll = position) }
    }
    fun getGenres() = viewModelScope.launch {
        if (!uiState.value.isLoading) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            genreRepository.getGenres(page).onSuccess { items ->
                if (items.isEmpty()) {
                    hasMore = false
                    _uiState.update {
                        val items = it.items
                        it.copy(
                            items = items,
                            isLoading = false
                        )
                    }
                } else {
                    hasMore = true
                    _uiState.update {
                        val items = it.items.plus(mapperAllGenre.mapToUI(items))
                        it.copy(
                            items = items,
                            isLoading = false
                        )
                    }
                    page++
                }
            }.onError {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}