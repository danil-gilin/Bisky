package com.example.bisky.ui.screen.homescreen.containehomescreen

import androidx.lifecycle.ViewModel
import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerView.Event
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.HomeTab
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.HomeTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeContainerViewModel @Inject constructor()  : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContainerView.State())
    val uiState: StateFlow<HomeContainerView.State> = _uiState

    fun onEvent(event: Event) {
        when(event) {
          is Event.OnTabSelected -> onTabSelected(event.position)
        }
    }

    private fun onTabSelected(type: HomeTabType) {
        val tabs =  listOf(
            HomeTab(name = R.string.title_new_series, isSelected = type ==  HomeTabType.New, type = HomeTabType.New ),
            HomeTab(name = R.string.title_season, isSelected = type ==  HomeTabType.Season , type = HomeTabType.Season ),
            HomeTab(name = R.string.title_genre, isSelected = type ==  HomeTabType.Genre , type = HomeTabType.Genre )
        )
        _uiState.update { it.copy(listTab = tabs, currentTabType = type) }
    }
}