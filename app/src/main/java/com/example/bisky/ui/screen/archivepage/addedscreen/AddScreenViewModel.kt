package com.example.bisky.ui.screen.archivepage.addedscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.CollectionRepository
import com.example.bisky.ui.screen.archivepage.addedscreen.AddScreenView.Event
import com.example.bisky.ui.screen.archivepage.addedscreen.mapper.AnimeAddMapper
import com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper.TextSearchUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
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
    var hasMore = true

    init {
        initData()
        subscribeAnimeCollection()
        subscribeSearchFlow()
    }

    private fun subscribeAnimeCollection() = viewModelScope.launch {
        collectionRepository
            .subscribeUserCollectionAnime(CollectionAnime.ADDED)
            .collectLatest {
                if (it.isEmpty()) {
                    hasMore = false
                }
                val items = animeAddMapper.mapToUI(it)
                _uiState.update {
                    it.copy(
                        isLoadingPagging = false,
                        isLoading = false,
                        items = items
                    )
                }
            }
    }

    private fun subscribeSearchFlow() = viewModelScope.launch {
        val searchFlow = _uiState.value.quickSelectUI.searchTextField.textAsFlow()
        searchFlow
            .collectLatest { query ->
                updateTextSearchUi(query)
                page = 1
                collectionRepository.getUserCollectionAnimePagging(
                    CollectionAnime.ADDED,
                    page,
                    query.toString(),
                    true
                )
                hasMore = true
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
                initData()
            }
            Event.OnSearchClick -> handleOnSearchClick()
            Event.OnGetMore -> handleOnGetMore()
        }
    }

    private fun handleOnGetMore() = viewModelScope.launch {
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
        )
    }

    private fun handleOnSearchClick() {
        _uiState.update {
            it.copy(
                quickSelectUI = it.quickSelectUI.copy(isSearchVisible = !it.quickSelectUI.isSearchVisible)
            )
        }
    }

    private fun initData() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }
        val searchText = _uiState.value.quickSelectUI.searchTextField.text.toString()
        hasMore = true
        page = 1
        collectionRepository.getUserCollectionAnimePagging(CollectionAnime.ADDED, page, searchText, true)
    }
}