package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.login.LoginRepositoryImpl
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInView.Event
import com.example.bisky.ui.screen.loginScreen.siginscreen.mapper.TextSigInUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SigInViewModel @Inject constructor(
    private val textUIMapper: TextSigInUIMapper,
    private val loginRepositoryImpl: LoginRepositoryImpl
) : ViewModel() {
    companion object {
        const val DEBOUNCE = 1000L
    }

    private val _uiState = MutableStateFlow(SigInView.State())
    val uiState: StateFlow<SigInView.State> = _uiState

    fun onEvent(event: Event) {
        when (event) {
            Event.OnSigInBtnClick -> onSigInBtnClick()
        }
    }

    private fun onSigInBtnClick() = viewModelScope.launch {
        val name = _uiState.value.emailTextField.text.toString()
        val password = _uiState.value.passwordTextField.text.toString()
        loginRepositoryImpl.sigIn(name, password).onSuccess {
            it
        }.onError {
            it
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
        _uiState.value.passwordTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants =
                    textUIMapper.mapTextAssistants(uiState.value.password, it.toString())
                _uiState.update { it.copy(password = itemAssistants) }
                delay(DEBOUNCE)
                val item = textUIMapper.passwordToTextUI(it.toString())
                _uiState.update { it.copy(password = item) }
            }
    }

    private suspend fun onEmailTextUpdate() {
        _uiState.value.emailTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants =
                    textUIMapper.mapTextAssistants(uiState.value.email, it.toString())
                _uiState.update { it.copy(email = itemAssistants) }
                delay(DEBOUNCE)
                val item = textUIMapper.mailToTextUI(it.toString())
                _uiState.update { it.copy(email = item) }
            }
    }
}
