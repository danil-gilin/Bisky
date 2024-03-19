package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.ui.navigation.NavigationRoute
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInView.Event
import com.example.bisky.ui.screen.loginScreen.siginscreen.mapper.TextSigInUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SigInViewModel @Inject constructor(
    private val textUIMapper: TextSigInUIMapper,
    private val loginRepositoryImpl: LoginRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SigInView.State())
    val uiState: StateFlow<SigInView.State> = _uiState

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnSigInBtnClick -> onSigInBtnClick(event.navController)
        }
    }

    private fun onSigInBtnClick(navController: NavController) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true,
                isErrorLogin = false
            )
        }
        val name = _uiState.value.loginTextField.text.toString()
        val password = _uiState.value.passwordTextField.text.toString()
        loginRepositoryImpl.sigIn(name, password).onSuccess {
            navController.navigate(NavigationRoute.Home.route) {
                popUpTo(NavigationRoute.SigIn.route) {
                    inclusive = true
                }
                popUpTo(NavigationRoute.BoardingLogin.route) {
                    inclusive = true
                }
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isErrorLogin = false
                )
            }
        }.onError {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isErrorLogin = true
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            launch {
                onPasswordTextUpdate()
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
                val itemAssistants =
                    textUIMapper.mapTextAssistants(uiState.value.password, it.toString())
                _uiState.update { it.copy(password = itemAssistants) }
            }
    }

    private suspend fun onLoginTextUpdate() {
        _uiState.value.loginTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants =
                    textUIMapper.mapTextAssistants(uiState.value.login, it.toString())
                _uiState.update { it.copy(login = itemAssistants) }
            }
    }
}
