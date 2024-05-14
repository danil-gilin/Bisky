package com.example.bisky.ui.screen.animescreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.collection.add.CollectionAddEventBus
import com.example.bisky.domain.eventbus.collection.add.CollectionAddEventBusEvent
import com.example.bisky.domain.eventbus.collection.completed.CollectionCompletedEventBus
import com.example.bisky.domain.eventbus.collection.completed.CollectionCompletedEventBusEvent
import com.example.bisky.domain.eventbus.collection.watching.CollectionWatchingEventBus
import com.example.bisky.domain.eventbus.collection.watching.CollectionWatchingEventBusEvent
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.domain.repository.anime.model.Anime
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.Event
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.State
import com.example.bisky.ui.screen.animescreen.mapper.AnimeFullInfoMapper
import com.example.bisky.ui.screen.animescreen.mapper.AnimeFullInfoMapper.Companion.ANIME_RATING_INFO
import com.example.bisky.ui.screen.animescreen.model.body.AnimeRatingUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeScreenViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val savedState: SavedStateHandle,
    private val animeFullInfoMapper: AnimeFullInfoMapper,
    private val collectionAddEventBus: CollectionAddEventBus,
    private val collectionWatchingEventBus: CollectionWatchingEventBus,
    private val collectionCompletedEventBus: CollectionCompletedEventBus
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    var animeDomain: Anime? = null
    init {
        viewModelScope.launch {
            getAnimeInfo()
        }
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnClickFullDescription -> onClickFullDescription(event.isFullInfoDescription)
            Event.OnCompleteScore -> onCompleteScore()
            Event.OnDeleteScoreClick -> onDeleteScoreClick()
            is Event.OnSelectScore -> onSelectScore(event.selectedScore)
            is Event.OnCollectionSelected -> onCollectionSelected(event.collectionType)
        }
    }

    private fun onClickFullDescription(fullInfoDescription: Boolean) {
        _uiState.update {
            it.copy(items = animeFullInfoMapper.updateDescriptionItem(it.items, fullInfoDescription))
        }
    }

    private fun onCompleteScore() = viewModelScope.launch {
        val item = uiState.value.items.find { it.itemId == ANIME_RATING_INFO } as? AnimeRatingUI ?: return@launch
        val animeId = savedState.get<String>("id") ?: return@launch
        val rating = item.selectedScore + 1
        animeRepository.updateAnimeScore(rating,animeId).onSuccess {
            getAnimeInfo()
        }.onError {
            it
        }
    }

    private fun onDeleteScoreClick() = viewModelScope.launch {
        val animeId = savedState.get<String>("id") ?: return@launch
        animeRepository.updateAnimeScore(null ,animeId).onSuccess {
            getAnimeInfo()
        }.onError {
            it
        }
    }

    private fun onSelectScore(selectedScore: Int) {
        _uiState.update {
            it.copy(items = animeFullInfoMapper.updateRatingScore(it.items, selectedScore))
        }
    }

    private fun onCollectionSelected(type: CollectionAnime) = viewModelScope.launch{
        val oldAnimeCollection = animeDomain?.userData?.collection
        val animeId = savedState.get<String>("id") ?: return@launch
        animeRepository.updateCollection(type,animeId).onSuccess {
            getAnimeInfo()
        }.onError {
            it
        }
        sendEventUpdateCollection(oldAnimeCollection)
        sendEventUpdateCollection(type)
    }

    private fun sendEventUpdateCollection(type: CollectionAnime?) = viewModelScope.launch{
        when(type) {
            CollectionAnime.ADDED ->
                collectionAddEventBus.emitEvent(CollectionAddEventBusEvent.UpdateCollection)
            CollectionAnime.COMPLETED ->
                collectionCompletedEventBus.emitEvent(CollectionCompletedEventBusEvent.UpdateCollection)
            CollectionAnime.WATCHING ->
                collectionWatchingEventBus.emitEvent(CollectionWatchingEventBusEvent.UpdateCollection)
            else -> Unit
        }
    }

    private suspend fun getAnimeInfo() {
        val id = savedState.get<String>("id") ?: return
        animeRepository.getAnime(id).onSuccess { anime ->
            if (anime == null) return
            animeDomain = anime
            _uiState.update {
                it.copy(items = animeFullInfoMapper.map(anime))
            }
        }.onError {
            it
        }
    }
}
