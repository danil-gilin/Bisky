package com.example.bisky.ui.screen.archivepage.quickselect

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.domain.repository.archive.model.AnimeQuickSelect
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.Event
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.State
import com.example.bisky.ui.screen.archivepage.quickselect.mapper.QuickSelectedMapper
import com.example.bisky.ui.screen.archivepage.watchsreen.WatchScreenView
import com.example.bisky.ui.screen.archivepage.watchsreen.mapper.AnimeWatchMapper
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.TextSearchUIMapper
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
    private val quickSelectMapper: QuickSelectedMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    var listAnimeSelected = emptyList<AnimeQuickSelect>()

    init {
        initData()
    }

    private fun initData() = viewModelScope.launch {
        collectionRepository.getUserCollectionQuickSelectAnime(
            CollectionAnime.COMPLETED,
            20
        ).onSuccess {
            listAnimeSelected = it
            updateSelectAnime(listAnimeSelected[0], listAnimeSelected[1])
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

    }

    private fun handleOnRightAnimeSelected() {

    }
}