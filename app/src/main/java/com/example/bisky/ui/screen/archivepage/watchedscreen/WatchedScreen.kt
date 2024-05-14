package com.example.bisky.ui.screen.archivepage.watchedscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.launch.LaunchAtTheEndOfList
import com.example.bisky.ui.elements.launch.lazyListStateWithListenerScroll
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.archivepage.container.item.QuickSearchSelectAnimeItem
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView.Event
import com.example.bisky.ui.screen.archivepage.watchedscreen.WatchedScreenView.State
import com.example.bisky.ui.screen.archivepage.watchedscreen.items.AnimeWatchedItems
import com.example.bisky.ui.screen.archivepage.watchedscreen.model.AnimeWatchedUI

@Composable
fun WatchedScreen(
    navController: NavController,
    viewModel: WatchedScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    WatchedScreen(
        uiState,
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        },
        onRefresh = {
            viewModel.onEvent(Event.OnRefresh)
        },
        onAnimeClick = {
            navController.navigate(Destination.Archive.Anime.route+"/$it")
        },
        onFilterClick = {
        },
        onSearchClick = {
            viewModel.onEvent(Event.OnSearchClick)
        },
        onQuickSelectClick = {
            navController.navigate(Destination.Archive.QuickSelectScreen.route + "/${CollectionAnime.COMPLETED}")
        },
        onGetMore = {
            viewModel.onEvent(Event.OnGetMore)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WatchedScreen(
    uiState: State,
    onScrollItem: (Int) -> Unit,
    onRefresh: () -> Unit,
    onAnimeClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onQuickSelectClick: () -> Unit,
    onFilterClick: () -> Unit,
    onGetMore: () -> Unit
) {
    val lazyListState = lazyListStateWithListenerScroll(
        uiState.positionScroll,
        onScrollItem
    )
    val pullRefreshState = rememberPullRefreshState(uiState.isLoading, { onRefresh() })
    lazyListState.LaunchAtTheEndOfList(
        onGetMore
    )
    Column {
        QuickSearchSelectAnimeItem(
            onSearchClick,
            onQuickSelectClick,
            onFilterClick,
            uiState.quickSelectUI,
            uiState.quickBtnEnabled
        )
        Box(Modifier.pullRefresh(pullRefreshState)) {
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
                        is AnimeWatchedUI -> AnimeWatchedItems(
                            it,
                            onAnimeClick,
                            Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
                item {
                    if (uiState.isLoadingPagging) {
                        ItemLoader()
                    } else {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
            PullRefreshIndicator(
                uiState.isLoading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WatchedScreenPreview() {
    WatchedScreen(
        State(),
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    )
}