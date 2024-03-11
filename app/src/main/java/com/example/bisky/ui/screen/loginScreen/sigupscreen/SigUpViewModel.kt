package com.example.bisky.ui.screen.loginScreen.sigupscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.sigupscreen.mapper.TextUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SigUpViewModel @Inject constructor(private val textUIMapper: TextUIMapper) : ViewModel() {
    companion object{
        const val DEBOUNCE = 1000L
    }

    private val _uiState = MutableStateFlow(SigUpView.State())
    val uiState: StateFlow<SigUpView.State> = _uiState


    init {
        viewModelScope.launch {
            launch {
                onPasswordTextUpdate()
            }
            launch {
                onEmailTextUpdate()
            }
            launch {
                onLoginTextUpdate()
            }
        }
    }

    private suspend fun onPasswordTextUpdate() {
        _uiState.value.passwordTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants = textUIMapper.mapTextAssistants(uiState.value.password, it.toString())
                _uiState.update { it.copy(password = itemAssistants) }
                delay(DEBOUNCE)
                val item = textUIMapper.passwordToTextUI(it.toString())
                _uiState.update { it.copy(password = item) }
            }
    }

    private suspend fun onLoginTextUpdate() {
        _uiState.value.loginTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants = textUIMapper.mapTextAssistants(uiState.value.login, it.toString())
                _uiState.update { it.copy(login = itemAssistants) }
                delay(DEBOUNCE)
                val item = textUIMapper.loginToTextUI(it.toString())
                _uiState.update { it.copy(login = item) }
            }
    }

    private suspend fun onEmailTextUpdate() {
        _uiState.value.emailTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants = textUIMapper.mapTextAssistants(uiState.value.email, it.toString())
                _uiState.update { it.copy(email = itemAssistants) }
                delay(DEBOUNCE)
                val item = textUIMapper.mailToTextUI(it.toString())
                _uiState.update { it.copy(email = item) }
            }
    }
}