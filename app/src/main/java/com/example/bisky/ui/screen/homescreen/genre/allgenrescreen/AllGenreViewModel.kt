package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.AllGenreView.Event
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.mapper.MapperAllGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            getGenres()
            _uiState.update { it.copy(positionScroll = 0) }
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
            Event.OnGetMore -> onGetMore()
        }
    }

    fun onGetMore() = viewModelScope.launch {
        getGenres()
    }

    suspend fun getGenres() {
        if (!uiState.value.isLoading && hasMore) {
            _uiState.update {
                it.copy(
                    items = it.items,
                    isLoading = true
                )
            }
            genreRepository.getGenres(page).onSuccess { items ->
                if (items.isEmpty()) {
                    hasMore = false
                    _uiState.update {
                        it.copy(
                            items = it.items,
                            isLoading = false
                        )
                    }
                } else {
                    hasMore = true
                    _uiState.update {
                        it.copy(
                            items = it.items.plus(mapperAllGenre.mapToUI(items)),
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
