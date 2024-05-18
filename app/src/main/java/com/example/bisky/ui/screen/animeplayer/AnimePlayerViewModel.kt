package com.example.bisky.ui.screen.animeplayer

import android.util.Log
import android.webkit.WebView
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.domain.eventbus.navigation.NavigationEventBus
import com.example.bisky.domain.eventbus.navigation.NavigationEventBusEvent
import com.example.bisky.ui.screen.animeplayer.AnimePlayerView.Action
import com.example.bisky.ui.screen.animeplayer.AnimePlayerView.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimePlayerViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val navigationEventBus: NavigationEventBus
): ViewModel() {
    var webView: WebView? = null

    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState

    init {
        navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(false))
        viewModelScope.launch {
            initData()
        }
    }


    private suspend fun initData() {
        val animeId = savedState.get<String>("id") ?: return
        val url = getUrl(animeId)
        _uiState.update {
            it.copy(
                url = getUrl(animeId)
            )
        }
        delay(1000L)
        _uiState.update {
            it.copy(
                showWebView = true
            )
        }
        Log.d("AnimeUrl", getUrl(animeId))
    }

    fun onAction(action: Action) {
        when(action) {
            Action.OnBackClick ->  navigationEventBus.emitEvent(NavigationEventBusEvent.ChangeVisibleBottomNav(true))
        }
    }

    private fun getUrl(id:String) = "https://bisky.one/anime/$id/player"
}