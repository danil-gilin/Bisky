package com.example.bisky.ui.screen.animescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.State
import com.example.bisky.ui.screen.animescreen.items.header.HeaderAnimeItem
import com.example.bisky.ui.screen.animescreen.model.header.HeaderItemUI

@Composable
fun AnimeScreen(
    navController: NavController,
    viewModel: AnimeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    AnimeScreen(uiState)
}

@Composable
fun AnimeScreen(
    uiState: State
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, top = 50.dp, 16.dp, 0.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.bisky_dark_400))
    ) {
        items(
            uiState.items,
            key = { item -> item.itemId }
        ) {
            when(it) {
                is HeaderItemUI -> HeaderAnimeItem(it)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeScreenPreview() {
    AnimeScreen(State())
}
