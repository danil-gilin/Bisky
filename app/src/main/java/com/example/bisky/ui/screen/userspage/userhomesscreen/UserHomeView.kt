package com.example.bisky.ui.screen.userspage.userhomesscreen

interface UserHomeView {
    data class State(
        val stub: String = ""
    )

    sealed class Event {
       object OnLogoutClick: Event()
    }
}