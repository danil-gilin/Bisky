package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.siginscreen.model.TextSigInUI

interface SigInView {
    @OptIn(ExperimentalFoundationApi::class)
    data class State(
        val validate: List<String> = emptyList(),
        val emailTextField: TextFieldState = TextFieldState(),
        val passwordTextField: TextFieldState = TextFieldState(),
        val email: TextSigInUI = TextSigInUI(placeHolder = R.string.email_placeholder),
        val password: TextSigInUI = TextSigInUI(placeHolder = R.string.password_placeholder)
    )

    sealed class Event
}
