package com.example.bisky.ui.screen.searchscreen.quicksearch

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.Action
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.Event
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.State
import com.example.bisky.ui.screen.searchscreen.quicksearch.items.ControlButton
import com.example.bisky.ui.screen.searchscreen.quicksearch.items.HeaderQuickSearch
import com.example.bisky.ui.screen.searchscreen.quicksearch.items.animeCard.AnimeCard
import com.example.bisky.ui.screen.searchscreen.quicksearch.model.AnimeBackInfoUI
import com.example.bisky.ui.screen.searchscreen.quicksearch.model.AnimeFrontInfoUI

@Composable
fun QuickSearchScreen(
    navController: NavController,
    viewModel: QuickSearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    QuickSearchScreen(
        uiState,
        onBackClick = {
            viewModel.onAction(Action.ShowBottomNav)
            navController.popBackStack()
        },
        onBackAnimeClick = {
            viewModel.onEvent(Event.OnBackClick)
        },
        onLikeClick = {
            viewModel.onEvent(Event.OnLikeClick)
        },
        onDislikeClick = {
            viewModel.onEvent(Event.OnDislikeClick)
        },
        onClickMoreInfo = {

        }
    )
}

@Composable
fun QuickSearchScreen(
    uiState: State,
    onBackClick: () -> Unit,
    onBackAnimeClick:() -> Unit,
    onLikeClick:() -> Unit,
    onDislikeClick:() -> Unit,
    onClickMoreInfo: (Boolean) -> Unit
) {
    BackHandler {
        onBackClick()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        HeaderQuickSearch(
            modifier = Modifier.padding(top = 8.dp),
            onBackClick = onBackClick
        )
        AnimeCard(
            uiState.frontAnimeInfo,
            uiState.backAnimeInfo,
            onClickMoreInfo,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp, top = 24.dp)
        )
        ControlButton(
            onBackAnimeClick,
            onLikeClick,
            onDislikeClick,
            uiState.count,
            uiState.isBackVisible
        )
    }
}

@Composable
@Preview(showBackground = true)
fun QuickSearchScreenPreview() {
    QuickSearchScreen(
        State(
            frontAnimeInfo = AnimeFrontInfoUI.previewItemAnimeFront,
            backAnimeInfo = AnimeBackInfoUI.previewItemAnimeBackInfo
        ),
        {},
        onBackAnimeClick = {
        },
        onLikeClick = {
        },
        onDislikeClick = {
        },
        onClickMoreInfo = {
        }
    )
}