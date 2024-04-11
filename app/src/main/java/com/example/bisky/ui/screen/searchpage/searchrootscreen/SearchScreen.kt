package com.example.bisky.ui.screen.searchpage.searchrootscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.launch.LaunchAtTheEndOfGrid
import com.example.bisky.ui.elements.launch.lazyGridStateWithListenerScroll
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.searchpage.searchrootscreen.SearchView.Event
import com.example.bisky.ui.screen.searchpage.searchrootscreen.SearchView.State
import com.example.bisky.ui.screen.searchpage.searchrootscreen.items.EmptyMessage
import com.example.bisky.ui.screen.searchpage.searchrootscreen.items.HeaderSearch
import com.example.bisky.ui.screen.searchpage.searchrootscreen.items.SearchAnime
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchAnimeUI

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchScreen(
        uiState,
        onGetMore = {
            viewModel.onEvent(Event.OnGetMore)
        },
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        },
        onSearchClick = {
            viewModel.onEvent(Event.OnSearchClick)
        },
        onFilterClick = {
            navController.navigate(Destination.Search.Filter.route)
        },
        onAnimeClick = {
            navController.navigate("${Destination.Search.Anime.route}/$it")
        },
        onQuickSearchClick = {
            navController.navigate(Destination.Search.QuickSearch.route)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    uiState: State,
    onGetMore: () -> Unit,
    onScrollItem: (Int) -> Unit,
    onSearchClick : () -> Unit,
    onQuickSearchClick : () -> Unit,
    onFilterClick : () -> Unit,
    onAnimeClick: (String) -> Unit
) {
    val listState = lazyGridStateWithListenerScroll(
        uiState.positionScroll,
        onScrollItem
    )
    listState.LaunchAtTheEndOfGrid(onGetMore)
    Box {
        Column(modifier = Modifier.background(colorResource(id = R.color.bisky_dark_400))) {
            HeaderSearch(
                onSearchClick,
                onQuickSearchClick,
                onFilterClick,
                uiState.isSearchInputVisible,
                uiState.searchTextField,
                uiState.searchUI
            )
            LazyVerticalGrid(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.bisky_dark_400)),
                columns = GridCells.Adaptive(100.dp)
            ) {
                items(
                    uiState.items.size
                ) { index ->
                    when(val item = uiState.items[index]) {
                        is SearchAnimeUI -> SearchAnime(searchAnimeUI = item, onAnimeClick)
                    }
                }
                items(count = 3) { index ->
                    if (uiState.isLoading && index == 1) {
                        ItemLoader()
                    }
                }
            }
        }
        if (uiState.isEmptyResult) {
            EmptyMessage(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreen() {
    SearchScreen(
        State(),
        onScrollItem = {},
        onFilterClick = {},
        onGetMore = {},
        onSearchClick = {},
        onAnimeClick = {},
        onQuickSearchClick ={}
    )
}