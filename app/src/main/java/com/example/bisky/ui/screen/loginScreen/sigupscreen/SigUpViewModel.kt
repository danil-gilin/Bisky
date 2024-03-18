package com.example.bisky.ui.screen.loginScreen.sigupscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.ui.navigation.NavigationRoute
import com.example.bisky.ui.screen.loginScreen.sigupscreen.SigUpView.Event
import com.example.bisky.ui.screen.loginScreen.sigupscreen.mapper.TextUIMapper
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
class SigUpViewModel @Inject constructor(
    private val textUIMapper: TextUIMapper,
    private val loginRepositoryImpl: LoginRepository
) : ViewModel() {
    companion object {
        const val DEBOUNCE = 1000L
    }

    private val _uiState = MutableStateFlow(SigUpView.State())
    val uiState: StateFlow<SigUpView.State> = _uiState

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnSigUpBtnClick -> onSigUpBtnClick(event.navController)
        }
    }

    fun onSigUpBtnClick(navController: NavController) = viewModelScope.launch {
        val name = _uiState.value.loginTextField.text.toString()
        val password = _uiState.value.passwordTextField.text.toString()
        val email = _uiState.value.emailTextField.text.toString()
        if (checkValidData()) {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    isBtnSigUpEnabled = false
                )
            }
            loginRepositoryImpl.sigUp(name, password, email).onSuccess {
                _uiState.update {
                    it.copy(
                        errorMsg = null
                    )
                }
                navController.navigate(NavigationRoute.Home.route) {
                    popUpTo(NavigationRoute.SigIn.route) {
                        inclusive = true
                    }
                    popUpTo(NavigationRoute.SigUp.route) {
                        inclusive = true
                    }
                }
            }.onError {
                // TODO refactor if server send normal error
                _uiState.update {
                    it.copy(
                        errorMsg = R.string.validSigUp
                    )
                }
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isBtnSigUpEnabled = true
                )
            }
        }
    }

    fun checkValidData(): Boolean {
        val item = textUIMapper.passwordToTextUI(_uiState.value.passwordTextField.text.toString())
        _uiState.update { it.copy(password = item) }
        val itemEmail = textUIMapper.mailToTextUI(_uiState.value.emailTextField.text.toString())
        _uiState.update { it.copy(email = itemEmail) }
        with(uiState.value) {
            return login.validateMsg == null &&
                email.validateMsg == null &&
                password.validateMsg == null
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
                delay(DEBOUNCE)
                val item = textUIMapper.passwordToTextUI(it.toString())
                _uiState.update { it.copy(password = item) }
            }
    }

    private suspend fun onLoginTextUpdate() {
        _uiState.value.loginTextField
            .textAsFlow()
            .collectLatest {
                val itemAssistants =
                    textUIMapper.mapTextAssistants(uiState.value.login, it.toString())
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
                val itemAssistants =
                    textUIMapper.mapTextAssistants(uiState.value.email, it.toString())
                _uiState.update { it.copy(email = itemAssistants) }
                delay(DEBOUNCE)
                val item = textUIMapper.mailToTextUI(it.toString())
                _uiState.update { it.copy(email = item) }
            }
    }
}
