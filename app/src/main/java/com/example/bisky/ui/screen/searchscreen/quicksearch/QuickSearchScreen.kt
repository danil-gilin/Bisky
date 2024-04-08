package com.example.bisky.ui.screen.searchscreen.quicksearch

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.Action
import com.example.bisky.ui.screen.searchscreen.quicksearch.QuickSearchView.State
import com.example.bisky.ui.screen.searchscreen.quicksearch.items.ControlButton
import com.example.bisky.ui.screen.searchscreen.quicksearch.items.HeaderQuickSearch

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
        },
        onLikeClick = {
        },
        onDislikeClick = {
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
        ControlButton(
            onBackAnimeClick,
            onLikeClick,
            onDislikeClick,
            uiState.count
        )
    }
}

@Composable
@Preview(showBackground = true)
fun QuickSearchScreenPreview() {
    QuickSearchScreen(
        State(),
        {},
        onBackAnimeClick = {
        },
        onLikeClick = {
        },
        onDislikeClick = {
        }
    )
}