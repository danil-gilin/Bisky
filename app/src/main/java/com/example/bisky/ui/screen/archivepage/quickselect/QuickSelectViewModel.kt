package com.example.bisky.ui.screen.archivepage.quickselect

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.domain.repository.archive.model.AnimeQuickSelect
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.Action
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.Event
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.State
import com.example.bisky.ui.screen.archivepage.quickselect.mapper.QuickSelectedMapper
import com.example.bisky.ui.screen.archivepage.quickselect.model.SelectAnimeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class QuickSelectViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val quickSelectMapper: QuickSelectedMapper,
    private val savedState: SavedStateHandle,
    private val navigationEventBus: NavigationEventBus
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    var listAnimeSelected = emptyList<AnimeQuickSelect>()
    var iteratorAnime = 0
    var leftIndex = 0
    var rightIndex = 1

    init {
        navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(false))
        initData()
    }

    private fun initData() = viewModelScope.launch {
        val collection = savedState.get<String>("collection") ?: return@launch
        collectionRepository.getUserCollectionQuickSelectAnime(
            CollectionAnime.valueOf(collection),
            10
        ).onSuccess {
            listAnimeSelected = it
            updateSelectAnime(listAnimeSelected[iteratorAnime], listAnimeSelected[++iteratorAnime])
            _uiState.update {
                it.copy(
                    currentPositionSelectAnime = 2,
                    allCountSelectedAnime = listAnimeSelected.size,
                    collection = CollectionAnime.valueOf(collection)
                )
            }
        }.onError {
            it
            // TODO
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.OnLeftSelect -> handleOnLeftAnimeSelected()
            Event.OnRightSelect -> handleOnRightAnimeSelected()
            Event.OnPreviewSelectedClick -> Unit
        }
    }



    fun onAction(action: Action.ShowBottomNav) {
        when (action) {
            Action.ShowBottomNav -> navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(true))
        }
    }

    private fun updateSelectAnime(
        animeLeft: AnimeQuickSelect,
        animeRight: AnimeQuickSelect
    ) {
        _uiState.update {
            it.copy(
                leftAnimeUI = quickSelectMapper.mapSelectAnimeToUI(animeLeft),
                rightAnimeUI = quickSelectMapper.mapSelectAnimeToUI(animeRight)
            )
        }
    }

    private fun handleOnLeftAnimeSelected() {
        if (iteratorAnime == listAnimeSelected.size - 1) {
            selectWinnerAnime(_uiState.value.leftAnimeUI, false)
            return
        }
        iteratorAnime++
        rightIndex = iteratorAnime
        val animeRight = listAnimeSelected[rightIndex]
        _uiState.update {
            it.copy(
                rightAnimeUI = quickSelectMapper.mapSelectAnimeToUI(animeRight),
                currentPositionSelectAnime = iteratorAnime+1
            )
        }
    }

    private fun handleOnRightAnimeSelected() {
        if (iteratorAnime == listAnimeSelected.size - 1) {
            selectWinnerAnime(_uiState.value.rightAnimeUI, true)
            return
        }
        iteratorAnime++
        leftIndex = iteratorAnime
        val animeLeft = listAnimeSelected[leftIndex]
        _uiState.update {
            it.copy(
                leftAnimeUI = quickSelectMapper.mapSelectAnimeToUI(animeLeft),
                currentPositionSelectAnime = iteratorAnime + 1
            )
        }
    }

    private fun selectWinnerAnime(winnerAnime: SelectAnimeUI, isRight: Boolean) {
        _uiState.update {
            it.copy(
                isWinnerScreen = true,
                winner = winnerAnime,
                winnerIsRight = isRight
            )
        }
    }
}