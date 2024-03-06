package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInView.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SigInViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SigInView.State())
    val uiState: StateFlow<SigInView.State> = _uiState

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnEmailTextUpdate -> Unit
            is Event.OnPasswordTextUpdate -> Unit
        }
    }

    init {
        viewModelScope.launch {
            launch {
                onPasswordTextUpdate()
            }
            launch {
                onEmailTextUpdate()
            }
        }
    }

    private suspend fun onPasswordTextUpdate() {
        _uiState.value.password
            .textAsFlow()
            .debounce(500L)
            .collectLatest {
                validatePassword(it)
            }
    }

    private suspend fun onEmailTextUpdate() {
        _uiState.value.email
            .textAsFlow()
            .debounce(500L)
            .collectLatest {
                validateEmail(it)
            }
    }

    private fun validatePassword(password: TextFieldCharSequence) {
        if (password.length < 6) {
            _uiState.update { it.copy(passwordBorderColor = R.color.red) }
        } else {
            _uiState.update { it.copy(passwordBorderColor = R.color.gray) }
        }
    }
    private fun validateEmail(email: TextFieldCharSequence) {
        if (email.length > 6) {
            _uiState.update { it.copy(emailBorderColor = R.color.red) }
        } else {
            _uiState.update { it.copy(emailBorderColor = R.color.gray) }
        }
    }
}