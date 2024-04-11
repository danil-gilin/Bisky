package com.example.bisky.ui.screen.searchpage.quicksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.searchanime.SearchAnimeRepository
import com.example.bisky.domain.repository.searchanime.model.AnimeQuickSearch
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchView.Action
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchView.Event
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchView.State
import com.example.bisky.ui.screen.searchpage.quicksearch.mapper.QuickSearchAnimeMapper
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeStatusSelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuickSearchViewModel @Inject constructor(
    private val navigationEventBus: NavigationEventBus,
    private val searchAnimeRepository: SearchAnimeRepository,
    private val quickSearchAnimeMapper: QuickSearchAnimeMapper,
    private val animeRepository: AnimeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    var listQuickSearchAnime = listOf<AnimeQuickSearch>()
    var mapAnimeStatusSelect = mutableMapOf<String, AnimeStatusSelect>()
    var maxCount = 10

    init {
        navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(false))
        getQuickSearchAnime()
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.OnBackClick -> onBackClick()
            is Event.OnDislikeClick -> onDislikeClick(event.id)
            is Event.OnLikeClick -> onLikeClick(event.id)
            is Event.OnLikeClick -> onLikeClick(event.id)
            Event.OnMoreInfoClick -> onMoreInfoClick()
        }
    }

    private fun onMoreInfoClick() {
        val backInfo = uiState.value.backAnimeInfo
        val updateBackInfo = quickSearchAnimeMapper.updateFullDescription(backInfo)
        _uiState.update {
            it.copy(
                backAnimeInfo = updateBackInfo
            )
        }
    }
    private fun onLikeClick(id: String) {
        mapAnimeStatusSelect[id] = AnimeStatusSelect.Like
        val count = uiState.value.controlButtonUI.count.toInt() - 1
        handleAnimeSelectedUpdate(count)
    }

    private fun onDislikeClick(id: String) {
        mapAnimeStatusSelect[id] = AnimeStatusSelect.Dislike
        val count = uiState.value.controlButtonUI.count.toInt() - 1
        handleAnimeSelectedUpdate(count)
    }

    private fun onBackClick() {
        val count = uiState.value.controlButtonUI.count.toInt() + 1
        val indexAnime = maxCount - count
        val anime = listQuickSearchAnime.get(indexAnime)
        _uiState.update {
            it.copy(
                backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(anime),
                frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(anime),
                controlButtonUI = quickSearchAnimeMapper.mapToControlButtonUI(
                    count = count,
                    maxCount = maxCount,
                    mapAnimeStatusSelect[anime._id]
                )
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
        _uiState.update {
            it.copy(
                isInitLoading = true
            )
        }
        searchAnimeRepository.getQuickSearchAnimes().onSuccess {
            listQuickSearchAnime = it
            maxCount = listQuickSearchAnime.size
            initAnimeStatusSelect(it)
            val backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(
                it.first()
            )
            val frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(
                it.first()
            )
            _uiState.update {
                it.copy(
                    backAnimeInfo = backAnimeInfo,
                    frontAnimeInfo = frontAnimeInfo,
                    controlButtonUI = it.controlButtonUI.copy(
                        count = maxCount.toString(),
                        isCountVisible = true
                    ),
                    isInitLoading = false,
                    isAnimeScreenVisible = true
                )
            }
        }.onError {
            it
        }
    }

    private fun initAnimeStatusSelect(animeQuickSearches: List<AnimeQuickSearch>) {
        animeQuickSearches.forEach {
            mapAnimeStatusSelect[it._id] = AnimeStatusSelect.None
        }
    }

    private fun handleUpdateAnimeCollection(
        listAddedAnime: List<String>
    ) = viewModelScope.launch{
        _uiState.update {
            it.copy(
                isAddedLoading = true
            )
        }
        val request = listAddedAnime.map {
            async {
                animeRepository.updateCollection(CollectionAnime.ADDED, it).onError {
                    it
                }
            }
        }
        request.awaitAll()
        _uiState.update {
            it.copy(
                isAddedLoading = false,
                isFinishedScreenVisible = true,
            )
        }
    }

    private fun handleAnimeSelectedUpdate(
        count: Int
    ) {
        if (count == 0) {
            val listAddedAnime = mapAnimeStatusSelect.mapNotNull {
                if (it.value == AnimeStatusSelect.Like) {
                    it.key
                } else {
                    null
                }
            }
            handleUpdateAnimeCollection(listAddedAnime)
            return
        }
        val indexAnime = maxCount - count

        val anime = listQuickSearchAnime[indexAnime]

        _uiState.update {
            it.copy(
                backAnimeInfo = quickSearchAnimeMapper.mapToAnimeBackUI(anime),
                frontAnimeInfo = quickSearchAnimeMapper.mapToAnimeFrontUI(anime),
                controlButtonUI = quickSearchAnimeMapper.mapToControlButtonUI(
                    count = count,
                    maxCount = maxCount,
                    mapAnimeStatusSelect[anime._id]
                )
            )
        }
    }
}