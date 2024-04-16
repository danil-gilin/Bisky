package com.example.bisky.ui.navigation.elements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent.ChangeVisibleBottomNav
import com.example.bisky.ui.navigation.ext.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationEventBus: NavigationEventBus
) : ViewModel() {
    private val _uiState = MutableStateFlow(NavigationView.State())
    val uiState: StateFlow<NavigationView.State> = _uiState

    var navController: NavController? = null
    init {
        subscribeNavigationEventBus()
    }
    fun initNavController(navController: NavController) {
       this.navController = navController
    }

    private fun subscribeNavigationEventBus() = viewModelScope.launch {
        navigationEventBus
            .eventsFlow
            .collectLatest { event ->
                when (event) {
                    is ChangeVisibleBottomNav -> updateBottomNav(event.isVisible)
                    NavigationEventBusEvent.LogOut -> logOut()
                }
            }
    }

    private fun logOut() {
        navController?.navigate(NavigationRoute.BoardingLogin.route){
            popUpTo(NavigationRoute.Home.route) {
                inclusive = true
            }
        }
    }

    private fun updateBottomNav(visible: Boolean) {
        _uiState.update {
            it.copy(
                isBottomNavVisible = visible
            )
        }
    }
}