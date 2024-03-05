package com.example.bisky.ui.screen.loginScreen.siginscreen

interface SigInView {
    data class State(
        val validate: List<String> = emptyList(),
        val email: String = "email",
        val password: String  = "password"
    )


    sealed class Event {
        data class OnPasswordChange(val str: String): Event()
        data class OnEmailChange(val str: String): Event()
    }
}