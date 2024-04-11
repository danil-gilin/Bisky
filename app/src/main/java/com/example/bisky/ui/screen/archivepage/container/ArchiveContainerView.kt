package com.example.bisky.ui.screen.archivepage.container

import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.ArchiveTab
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.ArchiveTabType

sealed interface ArchiveContainerView {
    data class State(
        val listTab: List<ArchiveTab> = listOf(
            ArchiveTab(name = R.string.title_watch, isSelected = true, type = ArchiveTabType.Watch),
            ArchiveTab(name = R.string.title_add, isSelected = false, type = ArchiveTabType.Add),
            ArchiveTab(name = R.string.title_watched, isSelected = false, type = ArchiveTabType.Watched)
        ),
        val currentTabType: ArchiveTabType = ArchiveTabType.Watch
    )

    sealed class Event {
        data class OnTabSelected(val position: ArchiveTabType) : Event()
    }

    sealed class UiLabel
}
