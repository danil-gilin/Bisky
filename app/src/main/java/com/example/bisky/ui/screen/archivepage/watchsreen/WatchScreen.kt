package com.example.bisky.ui.screen.archivepage.watchsreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.elements.launch.lazyListStateWithListenerScroll
import com.example.bisky.ui.screen.archivepage.watchsreen.WatchScreenView.Event
import com.example.bisky.ui.screen.archivepage.watchsreen.WatchScreenView.State
import com.example.bisky.ui.screen.archivepage.watchsreen.items.AnimeWatchItems
import com.example.bisky.ui.screen.archivepage.watchsreen.model.AnimeWatchUI

@Composable
fun WatchScreen(
    navController: NavController,
    viewModel: WatchScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    WatchScreen(
        uiState,
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        }
    )
}

@Composable
fun WatchScreen(
    uiState: State,
    onScrollItem: (Int) -> Unit
) {
    val lazyListState = lazyListStateWithListenerScroll(
        uiState.positionScroll,
        onScrollItem
    )
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(top = 16.dp),
        modifier = Modifier
            .background(color = colorResource(id = R.color.bisky_dark_400))
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        items(
            uiState.items,
            key = { it.itemId }
        ) {
            when (it) {
                is AnimeWatchUI -> AnimeWatchItems(
                    it,
                    Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WatchScreenPreview() {
    WatchScreen(
        State(
            items = listOf(
                AnimeWatchUI.preview
            )
        ),
        {}
    )
}