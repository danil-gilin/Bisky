package com.example.bisky.ui.screen.archivepage.watchedscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.collection.completed.CollectionCompletedEventBus
import com.example.bisky.domain.eventbus.collection.completed.CollectionCompletedEventBusEvent
import com.example.bisky.domain.eventbus.collection.watching.CollectionWatchingEventBusEvent
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.ui.screen.archivepage.addedscreen.AddScreenView
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView.Event
import com.example.bisky.ui.screen.archivepage.watchedscreen.mapper.AnimeWatchedMapper
import com.example.bisky.ui.screen.archivepage.watchsreen.WatchScreenView
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.TextSearchUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class WatchedScreenViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val animeWatchedMapper: AnimeWatchedMapper,
    private val searchUIMapper: TextSearchUIMapper,
    private val completedEventBus: CollectionCompletedEventBus
) : ViewModel() {
    private val _uiState = MutableStateFlow(WatchedScreenView.State())
    val uiState: StateFlow<WatchedScreenView.State> = _uiState


    @Volatile
    var page = 1

    @Volatile
    var hasMore = true


    init {
        subscribeSearchFlow()
        subscribeWatchingEventBusEvent()
    }

    private fun subscribeWatchingEventBusEvent() = viewModelScope.launch {
        completedEventBus
            .eventsFlow
            .collectLatest { event ->
                when(event) {
                    CollectionCompletedEventBusEvent.UpdateCollection -> handleRefresh()
                }
            }
    }

    private fun onGetMore(isRefreshing: Boolean = false) = viewModelScope.launch {
        val searchText = _uiState.value.quickSelectUI.searchTextField.text.toString()

        if (!uiState.value.isLoadingPagging && hasMore ) {
            _uiState.update {
                it.copy(
                    isLoadingPagging = !isRefreshing,
                    isLoading = isRefreshing,
                    quickBtnEnabled = it.items.size > 2
                )
            }
            collectionRepository.getUserCollectionAnimePagging(CollectionAnime.COMPLETED, page, searchText).onSuccess {
                val items = animeWatchedMapper.mapToUI(it)
                if (items.isEmpty()) {
                    hasMore = false
                    _uiState.update {
                        it.copy(
                            items = it.items,
                            isLoading = false,
                            isLoadingPagging = false,
                            quickBtnEnabled = it.items.size > 2
                        )
                    }
                } else {
                    hasMore = true
                    val newItems = if (isRefreshing) {
                        items
                    } else {
                        uiState.value.items.plus(items)
                    }
                    _uiState.update {
                        it.copy(
                            items = newItems,
                            isLoading = false,
                            isLoadingPagging = false,
                            quickBtnEnabled = newItems.size > 2
                        )
                    }
                    page++
                }
            }.onError {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoadingPagging = false,
                        quickBtnEnabled = false
                    )
                }
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
                handleRefresh()
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
            Event.OnRefresh -> handleRefresh()
            Event.OnSearchClick -> handleOnSearchClick()
            Event.OnGetMore -> onGetMore()
        }
    }

    private fun handleOnSearchClick() {
        _uiState.update {
            it.copy(
                quickSelectUI = it.quickSelectUI.copy(isSearchVisible = !it.quickSelectUI.isSearchVisible)
            )
        }
    }

    private fun handleRefresh() {
        page = 1
        hasMore = true
        onGetMore(true)
    }
}