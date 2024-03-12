package com.example.bisky.ui.screen.homescreen.containehomescreen.model

enum class HomeTabType {
    Season,
    Genre,
    New
}

data class HomeTab(val name: Int, val isSelected: Boolean, val type: HomeTabType)
