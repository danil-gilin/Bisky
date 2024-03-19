package com.example.bisky.ui.screen.homescreen.genre.genresscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.launch.LaunchAtTheEndOfGrid
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreenView.Event
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreenView.State
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.items.AnimeGenre
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.items.HeaderGenre
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.model.AnimeGenreUI

@Composable
fun GenreScreen(
    navController: NavController,
    viewModel: GenreScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    GenreScreen(
        uiState,
        onGetMore = {
            viewModel.onEvent(Event.OnGetMore)
        },
        onBackClick = {
            navController.popBackStack()
        }
    )
}


@Composable
fun GenreScreen(
    uiState: State,
    onGetMore: () -> Unit,
    onBackClick: () -> Unit
) {
    val listState = rememberLazyGridState()
    listState.LaunchAtTheEndOfGrid(onGetMore)
    Box(modifier = Modifier.background(colorResource(id = R.color.bisky_dark_400))) {
        LazyVerticalGrid(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp, top = 50.dp,end = 16.dp, bottom = 8.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.bisky_dark_400)),
            columns = GridCells.Adaptive(100.dp)
        ) {
            items(
                uiState.items.size
            ) { index ->
                when(val item = uiState.items[index]) {
                    is AnimeGenreUI -> AnimeGenre(animeGenreUI = item)
                }
            }
            items(count = 3) { index ->
                if (uiState.isLoading && index == 1) {
                    ItemLoader()
                }
            }
        }
        HeaderGenre(
            uiState.title,
            onBackClick
        )
    }
}

@Composable
@Preview(showBackground = true)
fun GenreScreenPreview() {
    GenreScreen(
        State().copy(
            items = listOf(
                AnimeGenreUI(
                    itemId = "itemId",
                    name = "anime anime anime anime anime",
                    img = "",
                    status = "released",
                    statusColor = R.color.lime,
                    score = "8.0",
                    scoreColor = R.color.lime,
                    isScoreVisible = true
                ),
                AnimeGenreUI(
                    itemId = "itemId",
                    name = "anime anime anime anime anime",
                    img = "",
                    status = "released",
                    statusColor = R.color.lime,
                    score = "8.0",
                    scoreColor = R.color.lime,
                    isScoreVisible = true
                ),
                AnimeGenreUI(
                    itemId = "itemId",
                    name = "anime anime anime anime anime",
                    img = "",
                    status = "released",
                    statusColor = R.color.lime,
                    score = "8.0",
                    scoreColor = R.color.lime,
                    isScoreVisible = true
                )
            )
        ),
        onGetMore = {},
        onBackClick = {}
    )
}