package com.example.bisky.ui.screen.loginScreen.sigupscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.sigupscreen.model.TextUI

interface SigUpView {
    @OptIn(ExperimentalFoundationApi::class)
    data class State(
        val errorMsg: Int? = null,
        val email: TextUI = TextUI(placeHolder = R.string.email_placeholder),
        val login: TextUI = TextUI(placeHolder = R.string.login_placeholder),
        val password: TextUI = TextUI(placeHolder = R.string.password_placeholder),
        val emailTextField: TextFieldState = TextFieldState(),
        val passwordTextField: TextFieldState = TextFieldState(),
        val loginTextField: TextFieldState = TextFieldState(),
        val isBtnSigUpEnabled: Boolean = true,
        val isLoading: Boolean = false
    )

    sealed class Event {
        data class OnSigUpBtnClick(val navController: NavController) : Event()
    }
}
