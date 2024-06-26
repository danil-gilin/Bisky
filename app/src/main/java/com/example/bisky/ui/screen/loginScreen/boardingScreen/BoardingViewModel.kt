package com.example.bisky.ui.screen.loginScreen.boardingScreen

import androidx.lifecycle.ViewModel
import com.example.bisky.ui.screen.loginScreen.boardingScreen.BoardingView.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class BoardingViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState
}
