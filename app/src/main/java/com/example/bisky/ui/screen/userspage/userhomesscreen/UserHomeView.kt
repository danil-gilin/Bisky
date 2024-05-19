package com.example.bisky.ui.screen.userspage.userhomesscreen

interface UserHomeView {
    data class State(
        val stub: String = "",
        val userName: String = "",
        val userImg: Any = ""
    )

    sealed class Event {
       object OnLogoutClick: Event()
    }
}