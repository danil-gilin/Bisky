package com.example.bisky.ui.screen.homescreen.containehomescreen.model

enum class ArchiveTabType {
    Watch,
    Add,
    Watched
}

data class ArchiveTab(val name: Int, val isSelected: Boolean, val type: ArchiveTabType)
