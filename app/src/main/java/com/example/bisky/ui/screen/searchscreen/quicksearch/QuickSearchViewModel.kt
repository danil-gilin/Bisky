package com.example.bisky.ui.screen.searchscreen.quicksearch

import androidx.lifecycle.ViewModel
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.ui.screen.searchscreen.filterscreen.FilterView
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.Action
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuickSearchViewModel @Inject constructor(
    private val navigationEventBus: NavigationEventBus
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState



    init {
        navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(false))
    }

    fun onAction(action: Action) {
        when(action) {
            Action.ShowBottomNav -> navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(true))
        }
    }
}