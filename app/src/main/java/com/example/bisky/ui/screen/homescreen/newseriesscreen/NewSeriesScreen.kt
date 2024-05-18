package com.example.bisky.ui.screen.homescreen.newseriesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.elements.ItemLoader
import com.example.bisky.ui.elements.launch.LaunchAtTheEndOfList
import com.example.bisky.ui.elements.launch.lazyListStateWithListenerScroll
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.items.ItemGenre
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.model.GenreUI
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesView.Event
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesView.State
import com.example.bisky.ui.screen.homescreen.newseriesscreen.items.EmptyListMsg
import com.example.bisky.ui.screen.homescreen.newseriesscreen.items.NewSeriesAnimeItems
import com.example.bisky.ui.screen.homescreen.newseriesscreen.model.NewSeriesAnimeUI
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeViewModel

@Composable
fun NewSeriesScreen(
    navController: NavController,
    viewModel: NewSeriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NewSeriesScreen(
        uiState,
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        },
        onAnimeClick = { id ->
            navController.navigate(
                "${Destination.Home.Anime.route}/$id"
            )
        },
        onGetMore = {
            viewModel.onEvent(Event.OnGetMore)
        }
    )
}

@Composable
fun NewSeriesScreen(
    uiState: State,
    onScrollItem: (Int) -> Unit,
    onAnimeClick: (String) -> Unit,
    onGetMore: () -> Unit
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
            uiState.itemsAnime,
            key = { it.itemId }
        ) {
            when (it) {
                is NewSeriesAnimeUI -> NewSeriesAnimeItems(
                    it,
                    onAnimeClick
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
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        item {
            if (uiState.isEmptyState) {
                EmptyListMsg(stringResource(id = R.string.empty_new_series))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewSeriesScreenPreview() {
    NewSeriesScreen(
        State(),
        {},
        {},
        {}
    )
}
