package com.example.bisky.ui.screen.archivepage.container

import androidx.lifecycle.ViewModel
import com.example.bisky.R
import com.example.bisky.ui.screen.archivepage.addedscreen.AddScreenView
import com.example.bisky.ui.screen.archivepage.container.ArchiveContainerView.Event
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.ArchiveTab
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.ArchiveTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ArchiveContainerViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ArchiveContainerView.State())
    val uiState: StateFlow<ArchiveContainerView.State> = _uiState

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnTabSelected -> onTabSelected(event.position)
        }
    }

    private fun onTabSelected(type: ArchiveTabType) {
        val tabs = listOf(
            ArchiveTab(
                name = R.string.title_watch,
                isSelected = type == ArchiveTabType.Watch,
                type = ArchiveTabType.Watch
            ),
            ArchiveTab(
                name = R.string.title_add,
                isSelected = type == ArchiveTabType.Add,
                type = ArchiveTabType.Add
            ),
            ArchiveTab(
                name = R.string.title_watched,
                isSelected = type == ArchiveTabType.Watched,
                type = ArchiveTabType.Watched
            )
        )
        _uiState.update { it.copy(listTab = tabs, currentTabType = type) }
    }
}
