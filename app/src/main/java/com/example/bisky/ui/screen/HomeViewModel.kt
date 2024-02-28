package com.example.bisky.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.seasonanime.SeasonAnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val seasonAnimeRepository: SeasonAnimeRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenView.State())
    val uiState: StateFlow<HomeScreenView.State> = _uiState


    init {
        viewModelScope.launch {
            val result = seasonAnimeRepository.getSeasonAnime()
            result
        }
    }
}