package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.launch.LaunchAtTheEndOfList
import com.example.bisky.ui.elements.launch.lazyListStateWithListenerScroll
import com.example.bisky.ui.navigation.NavigationRoute.Genre
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.AllGenreView.Event
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.items.ItemGenre
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.model.GenreUI

@Composable
fun AllGenreScreen(
    navController: NavController,
    viewModel: AllGenreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    AllGenreScreen(
        uiState,
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        },
        onGetMore = {
            viewModel.onEvent(Event.OnGetMore)
        },
        onClickGenre = { id, name ->
            navController.navigate(
                "${Genre.route}/$id/$name"
            )
        }
    )
}

@Composable
fun AllGenreScreen(
    uiState: AllGenreView.State,
    onScrollItem: (Int) -> Unit,
    onGetMore: () -> Unit,
    onClickGenre: (String, String) -> Unit
) {
    val listState = lazyListStateWithListenerScroll(
        uiState.positionScroll,
        onScrollItem
    )
    listState.LaunchAtTheEndOfList(
        onGetMore
    )

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, top = 50.dp, 16.dp, 0.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.bisky_dark_400))
    ) {
        items(
            uiState.items,
            key = { it.itemId }
        ) {
            when (it) {
                is GenreUI -> ItemGenre(
                    genreUI = it,
                    onClickGenre
                )
            }
        }
        item(uiState.isLoading) {
            if (uiState.isLoading) {
                ItemLoader()
            } else {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AllGenreScreenPreview() {
    AllGenreScreen(
        AllGenreView.State(),
        onScrollItem = {},
        onGetMore = {},
        onClickGenre = { id, name ->}
    )
}
