package com.example.bisky.ui.navigation.elements

import com.example.bisky.common.model.BaseItem

interface NavigationView {
    data class State(
        val isBottomNavVisible: Boolean = true
    )
}