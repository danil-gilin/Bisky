package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.lifecycle.ViewModel
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInView.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SigInView.State())
    val uiState: StateFlow<SigInView.State> = _uiState

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnEmailChange -> _uiState.update { it.copy(email = event.str) }
            is Event.OnPasswordChange -> _uiState.update { it.copy(password = event.str) }
        }
    }
}