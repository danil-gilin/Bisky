package com.example.bisky.ui.screen.animescreen

import com.example.bisky.common.model.BaseItem
import com.example.bisky.domain.repository.anime.model.CollectionAnime

interface AnimeScreenView {

    data class State(
        val isLoading: Boolean = false,
        val isFullDescription: Boolean = false,
        val items: List<BaseItem> = emptyList()
    )

    sealed class Event {
        data class OnClickFullDescription(val isFullInfoDescription: Boolean) : Event()
        data object OnDeleteScoreClick : Event()
        data class OnSelectScore(val selectedScore: Int) : Event()
        data class OnCollectionSelected(val collectionType: CollectionAnime) : Event()
        data object OnCompleteScore : Event()
    }
}
