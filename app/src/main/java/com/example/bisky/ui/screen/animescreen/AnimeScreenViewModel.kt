package com.example.bisky.ui.screen.animescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    init {
        viewModelScope.launch {
            getAnimeInfo()
        }
    }

    private suspend fun getAnimeInfo() {
    }
}
