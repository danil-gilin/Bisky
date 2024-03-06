package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.example.bisky.R

interface SigInView {
    @OptIn(ExperimentalFoundationApi::class)
    data class State(
        val validate: List<String> = emptyList(),
        val email: TextFieldState = TextFieldState("email"),
        val password: TextFieldState = TextFieldState("password"),
        val passwordBorderColor: Int =  R.color.gray,
        val emailBorderColor: Int =  R.color.gray,
    )


    sealed class Event {
        data class OnPasswordTextUpdate(val str: String): Event()
        data class OnEmailTextUpdate(val str: String): Event()
    }
}