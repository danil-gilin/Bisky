package com.example.bisky.ui.screen.searchscreen.quicksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.domain.repository.searchanime.model.AnimeQuickSearch
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.Action
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.Event
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.State
import com.example.bisky.ui.screen.searchscreen.quicksearch.mapper.QuickSearchAnimeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuickSearchViewModel @Inject constructor(
    private val navigationEventBus: NavigationEventBus,
    private val searchAnimeRepository: SearchAnimeRepository,
    private val quickSearchAnimeMapper: QuickSearchAnimeMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    var listQuickSearchAnime = listOf<AnimeQuickSearch>()
    var maxCount = 10

    init {
        navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(false))
        getQuickSearchAnime()
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.OnBackClick -> onBackClick()
            Event.OnDislikeClick -> onDislikeClick()
            Event.OnLikeClick -> onLikeClick()
        }
    }

    private fun onLikeClick() {
        val count = uiState.value.count.toInt() - 1
        if (count == 0) return
        val indexAnime = maxCount - count
        val anime = listQuickSearchAnime[indexAnime]
        val backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(anime)
        val frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(anime)
        _uiState.update {
            it.copy(
                backAnimeInfo = backAnimeInfo,
                frontAnimeInfo = frontAnimeInfo,
                count = count.toString(),
                isBackVisible = count < maxCount
            )
        }
    }

    private fun onDislikeClick() {
        val count = uiState.value.count.toInt() - 1
        if (count == 0) return
        val indexAnime = maxCount - count
        val anime = listQuickSearchAnime.get(indexAnime)
        val backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(anime)
        val frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(anime)
        _uiState.update {
            it.copy(
                backAnimeInfo = backAnimeInfo,
                frontAnimeInfo = frontAnimeInfo,
                count = count.toString(),
                isBackVisible = count < maxCount
            )
        }
    }

    private fun onBackClick() {
        val count = uiState.value.count.toInt() + 1
        if (count == 0) return
        val indexAnime = maxCount - count
        val anime = listQuickSearchAnime.get(indexAnime)
        val backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(anime)
        val frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(anime)
        _uiState.update {
            it.copy(
                backAnimeInfo = backAnimeInfo,
                frontAnimeInfo = frontAnimeInfo,
                count = count.toString(),
                isBackVisible = count < maxCount
            )
        }
    }

    fun onAction(action: Action) {
        when (action) {
            Action.ShowBottomNav -> navigationEventBus.emitEvent(
                NavigationEventBusEvent.ChangeVisibleBottomNav(
                    true
                )
            )
        }
    }

    private fun getQuickSearchAnime() = viewModelScope.launch {
        searchAnimeRepository.getQuickSearchAnimes().onSuccess {
            listQuickSearchAnime = it
            maxCount = listQuickSearchAnime.size
            val backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(it.first())
            val frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(it.first())
            _uiState.update {
                it.copy(
                    backAnimeInfo = backAnimeInfo,
                    frontAnimeInfo = frontAnimeInfo,
                    count = maxCount.toString()
                )
            }
        }.onError {
            it
        }
    }
}