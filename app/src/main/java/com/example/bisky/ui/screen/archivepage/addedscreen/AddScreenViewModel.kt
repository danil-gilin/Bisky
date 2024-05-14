package com.example.bisky.ui.screen.archivepage.addedscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.ui.screen.archivepage.addedscreen.AddScreenView.Event
import com.example.bisky.ui.screen.archivepage.addedscreen.mapper.AnimeAddMapper
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.TextSearchUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val animeAddMapper: AnimeAddMapper,
    private val searchUIMapper: TextSearchUIMapper
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddScreenView.State())
    val uiState: StateFlow<AddScreenView.State> = _uiState


    @Volatile
    var page = 2

    @Volatile
    var hasMore = false

    init {
        subscribeAnimeCollection()
        subscribeSearchFlow()
    }

    private fun subscribeAnimeCollection() = viewModelScope.launch {
        collectionRepository
            .subscribeUserCollectionAnime(CollectionAnime.ADDED)
            .distinctUntilChanged()
            .collectLatest {
                val items = animeAddMapper.mapToUI(it)
                _uiState.update {
                    it.copy(
                        items = items,
                        quickBtnEnabled = items.size > 2
                    )
                }
            }
    }

    @OptIn(FlowPreview::class)
    private fun subscribeSearchFlow() = viewModelScope.launch {
        val searchFlow = _uiState.value.quickSelectUI.searchTextField.textAsFlow()
        searchFlow
            .map { query ->
                updateTextSearchUi(query)
                query.toString()
            }
            .distinctUntilChanged()
            .debounce(500L)
            .collectLatest { query ->
                initData(1, query)
                handleOnGetMore()
            }
    }

    private fun updateTextSearchUi(query: TextFieldCharSequence) {
        val searchUI = searchUIMapper.map(query.toString())
        _uiState.update {
            it.copy(
                quickSelectUI = it.quickSelectUI.copy(searchUI = searchUI)
            )
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
            Event.OnRefresh -> {
                val searchText = _uiState.value.quickSelectUI.searchTextField.text.toString()
                initData(1, searchText)
                handleOnGetMore()
            }

            Event.OnSearchClick -> handleOnSearchClick()
            Event.OnGetMore -> handleOnGetMore()
        }
    }

    private fun handleOnGetMore() = viewModelScope.launch {
        if (!hasMore) {
            pagingLoadEnd()
            return@launch
        }
        _uiState.update {
            it.copy(
                isLoadingPagging = true
            )
        }
        val searchText = _uiState.value.quickSelectUI.searchTextField.text.toString()
        page++
        collectionRepository.getUserCollectionAnimePagging(
            CollectionAnime.ADDED,
            page,
            searchText,
            false
        ).onSuccess {
            if (it.isEmpty()) {
                pagingLoadEnd()
            }
        }.onError {
            pagingLoadEnd()
        }
    }

    private fun handleOnSearchClick() {
        _uiState.update {
            it.copy(
                quickSelectUI = it.quickSelectUI.copy(isSearchVisible = !it.quickSelectUI.isSearchVisible)
            )
        }
    }

    private fun initData(
        pages: Int,
        textSearch: String
    ) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        page = pages
        collectionRepository.getUserCollectionAnimePagging(CollectionAnime.ADDED, page, textSearch, true)
        hasMore = true
        refreshLoadEnd()
    }

    private fun refreshLoadEnd() {
        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    private fun pagingLoadEnd() {
        _uiState.update {
            it.copy(
                isLoadingPagging = false
            )
        }
    }
}