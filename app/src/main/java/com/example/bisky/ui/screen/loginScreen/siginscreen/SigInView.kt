package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.siginscreen.model.TextSigInUI

interface SigInView {
    @OptIn(ExperimentalFoundationApi::class)
    data class State(
        val loginTextField: TextFieldState = TextFieldState(),
        val passwordTextField: TextFieldState = TextFieldState(),
        val login: TextSigInUI = TextSigInUI(placeHolder = R.string.login_placeholder),
        val password: TextSigInUI = TextSigInUI(placeHolder = R.string.password_placeholder),
        val isLoading: Boolean = false,
        val isErrorLogin: Boolean = false
    )

    sealed class Event {
        data class OnSigInBtnClick(val navController: NavController) : Event()
    }
}
