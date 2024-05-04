package com.example.bisky.ui.screen.searchpage.quicksearch

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchView.Action
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchView.Event
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchView.State
import com.example.bisky.ui.screen.searchpage.quicksearch.items.ControlButton
import com.example.bisky.ui.screen.searchpage.quicksearch.items.HeaderQuickSearch
import com.example.bisky.ui.screen.searchpage.quicksearch.items.animeCard.AnimeCard
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeBackInfoUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeFrontInfoUI

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
            viewModel.onEvent(Event.OnLikeClick(it))
        },
        onDislikeClick = {
            viewModel.onEvent(Event.OnDislikeClick(it))
        },
        onClickMoreInfo = {
            viewModel.onEvent(Event.OnMoreInfoClick)
        }
    )
}

@Composable
fun QuickSearchScreen(
    uiState: State,
    onBackClick: () -> Unit,
    onBackAnimeClick: () -> Unit,
    onLikeClick: (String) -> Unit,
    onDislikeClick: (String) -> Unit,
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
        if (uiState.isAddedLoading) {
            LoadingAddedScreen()
        }
        if (uiState.isInitLoading) {
            LoadingInitScreen()
        }
        if (uiState.isFinishedScreenVisible) {
            FinishedScreen(onBackClick)
        }
        if (uiState.isErrorScreenVisible) {
            ErrorScreen(onBackClick)
        }
        if (uiState.isAnimeScreenVisible) {
            AnimeCard(
                uiState.frontAnimeInfo,
                uiState.backAnimeInfo,
                onClickMoreInfo,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 56.dp, top = 42.dp)
            )
            ControlButton(
                onBackAnimeClick,
                onLikeClick = {
                    uiState.frontAnimeInfo?.itemId?.let { onLikeClick(it) }
                },
                onDislikeClick = {
                    uiState.frontAnimeInfo?.itemId?.let { onDislikeClick(it) }
                },
                uiState.controlButtonUI
            )
        }
    }
}

@Composable
private fun LoadingInitScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.quick_search_init_loading),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
            ItemLoader()
        }
    }
}

@Composable
private fun LoadingAddedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth()
        ) {
            Text(
                text = stringResource(id = R.string.quick_search_added_loading),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
            ItemLoader()
        }
    }
}

@Composable
private fun FinishedScreen(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth()
        ) {
            Text(
                text = stringResource(id = R.string.quick_search_finished_title),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 24.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
            Text(
                text = stringResource(id = R.string.quick_search_finished_btn),
                fontSize = 18.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.bisky_300))
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp, 16.dp, 24.dp, 16.dp)
                    .noRippleClickable {
                        onBackClick()
                    },
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
        }
    }
}

@Composable
private fun ErrorScreen(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth()
        ) {
            Text(
                text = stringResource(id = R.string.quick_search_error_title),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 24.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
            Text(
                text = stringResource(id = R.string.quick_search_finished_btn),
                fontSize = 18.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.bisky_300))
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp, 16.dp, 24.dp, 16.dp)
                    .noRippleClickable {
                        onBackClick()
                    },
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
        }
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

@Composable
@Preview(showBackground = true)
private fun LoadingAddedScreenPreview() {
    LoadingAddedScreen()
}

@Composable
@Preview(showBackground = true)
private fun LoadingInitScreenPreview() {
    LoadingInitScreen()
}

@Composable
@Preview(showBackground = true)
private fun FinishedScreenPreview() {
    FinishedScreen({})
}
@Composable
@Preview(showBackground = true)
private fun ErrorScreenPreview() {
    ErrorScreen({})
}
