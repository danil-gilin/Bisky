package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.bisky.R
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.launch.LazyListStateWithListenerScroll
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreenView.Event
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.items.ItemAnimeSeason
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.items.ItemHeaderSeason
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.AnimeSeasonUI

@Composable
fun SeasonAnimeScreen(
    viewModel: SeasonAnimeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SeasonAnimeScreen(
        uiState = uiState,
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        },
        onRefresh = {
            viewModel.onEvent(Event.OnRefresh)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SeasonAnimeScreen(
    uiState: SeasonAnimeScreenView.State,
    onScrollItem: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    val lazyListState = LazyListStateWithListenerScroll(
        uiState.positionScroll,
        onScrollItem
    )
    val pullRefreshState = rememberPullRefreshState(uiState.isRefreshing, { onRefresh() })
    val listAnime = uiState.itemsAnime

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 0.dp, top = 45.dp, 0.dp, 0.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.bisky_dark_400))
        ) {
            item {
                ItemHeaderSeason(uiState.seasonImg, uiState.seasonTitle)
            }
            items(
                listAnime,
                key = { it.itemId }
            ) { item ->
                ItemAnimeSeason(item)
            }
            item {
                if (uiState.isLoading) {
                    ItemLoader()
                }
            }
        }
        PullRefreshIndicator(
            uiState.isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SeasonAnimePreview() {
    SeasonAnimeScreen(
        SeasonAnimeScreenView.State(
            seasonImg = R.drawable.anime_autumn,
            seasonTitle = R.string.anime_autumn_title,
            positionScroll = 0,
            itemsAnime = listOf(
                AnimeSeasonUI(
                    itemId = "2",
                    img = "R.drawable.anime_autumn",
                    title = "anime anime anime",
                    description = "anime anime anime",
                    rating = "0.0",
                    ratingColor = R.color.bisky_200,
                    genre = "anime / anime / anime",
                    isRatingVisible = true,
                    backgroundImg = "R.drawable.anime_autumn"
                )
            )
        ),
        {},
        {}
    )
}
