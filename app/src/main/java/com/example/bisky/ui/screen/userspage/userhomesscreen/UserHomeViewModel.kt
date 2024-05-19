package com.example.bisky.ui.screen.userspage.userhomesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.ui.screen.userspage.userhomesscreen.UserHomeView.Event
import com.example.bisky.ui.screen.userspage.userhomesscreen.UserHomeView.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(
   private val loginRepository: LoginRepository,
   private val navigationEventBus: NavigationEventBus
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    fun onEvent(event: Event) {
        when(event) {
           is Event.OnLogoutClick -> onLogoutClick()
        }
    }

    init {
        viewModelScope.launch {
            initData()
        }
    }

    private suspend fun initData() {
        val user = loginRepository.fetchUser()
        _uiState.update {
            it.copy(
                userName = user?.username.orEmpty(),
                userImg = user?.avatar ?: R.drawable.profile_test_img
            )
        }
    }

    private fun onLogoutClick() = viewModelScope.launch {
        loginRepository.sigOut().onSuccess {
            navigationEventBus.emitEvent(NavigationEventBusEvent.LogOut)
        }.onError {
            navigationEventBus.emitEvent(NavigationEventBusEvent.LogOut)
        }
    }
}