package com.example.bisky.ui.screen.animescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.Event
import com.example.bisky.ui.screen.animescreen.AnimeScreenView.State
import com.example.bisky.ui.screen.animescreen.items.body.AnimeDescriptionItems
import com.example.bisky.ui.screen.animescreen.items.body.AnimeProducerInfoItem
import com.example.bisky.ui.screen.animescreen.items.body.AnimeRatingItem
import com.example.bisky.ui.screen.animescreen.items.body.AnimeScreenshotItem
import com.example.bisky.ui.screen.animescreen.items.body.AnimeSimilarItem
import com.example.bisky.ui.screen.animescreen.items.body.AnimeUserListItem
import com.example.bisky.ui.screen.animescreen.items.body.AnimeVideoItem
import com.example.bisky.ui.screen.animescreen.items.header.HeaderAnimeItem
import com.example.bisky.ui.screen.animescreen.model.body.AnimeDescriptionUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeProducerInfoUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeRatingUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeScreenshotsUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeUserListUI
import com.example.bisky.ui.screen.animescreen.model.body.AnimeVideoUI
import com.example.bisky.ui.screen.animescreen.model.body.SimilarAnimeListUI
import com.example.bisky.ui.screen.animescreen.model.header.HeaderItemUI

@Composable
fun AnimeScreen(
    navController: NavController,
    viewModel: AnimeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    AnimeScreen(
        uiState,
        onClickMoreInfo = {
            viewModel.onEvent(Event.OnClickFullDescription(it))
        },
        onClickAnime = { id ->
            navController.navigate(
                "${Destination.Home.Anime.route}/$id"
            )
        },
        onDeleteScoreClick = {
            viewModel.onEvent(Event.OnDeleteScoreClick)
        },
        onSelectScore = {
            viewModel.onEvent(Event.OnSelectScore(it))
        },
        onCompleteScore = {
            viewModel.onEvent(Event.OnCompleteScore)
        },
        onCollectionSelected = {
            viewModel.onEvent(Event.OnCollectionSelected(it))
        },
        onBackClicked = {
            navController.popBackStack()
        },
        onPlayClick = { id ->
            navController.navigate(
                "${Destination.Home.AnimePlayer.route}/$id"
            )
        }
    )
}

@Composable
fun AnimeScreen(
    uiState: State,
    onClickMoreInfo: (Boolean) -> Unit,
    onClickAnime: (String) -> Unit,
    onDeleteScoreClick: () -> Unit,
    onSelectScore: (Int) -> Unit,
    onCompleteScore: () -> Unit,
    onCollectionSelected: (CollectionAnime) -> Unit,
    onBackClicked: () -> Unit,
    onPlayClick: (String) -> Unit
) {
    Box {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.bisky_dark_400))
        ) {
            items(
                uiState.items,
                key = { item -> item.itemId }
            ) {
                when (it) {
                    is HeaderItemUI -> HeaderAnimeItem(it, onCollectionSelected, onBackClicked)
                    is AnimeDescriptionUI -> AnimeDescriptionItems(
                        it,
                        onClickMoreInfo = onClickMoreInfo
                    )

                    is AnimeProducerInfoUI -> AnimeProducerInfoItem(it)
                    is AnimeScreenshotsUI -> AnimeScreenshotItem(it)
                    is AnimeUserListUI -> AnimeUserListItem(it)
                    is AnimeVideoUI -> AnimeVideoItem(it)
                    is SimilarAnimeListUI -> AnimeSimilarItem(it, onClickAnime)
                    is AnimeRatingUI -> AnimeRatingItem(
                        it,
                        onDeleteScoreClick,
                        onSelectScore,
                        onCompleteScore
                    )
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp, bottom = 60.dp),
            onClick = {
                onPlayClick(uiState.animeId)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.bisky_200)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = stringResource(id = R.string.watch))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeScreenPreview() {
    AnimeScreen(
        State(),
        onClickMoreInfo = {},
        onClickAnime = {},
        onDeleteScoreClick = {

        },
        onSelectScore = {

        },
        onCompleteScore = {

        },
        {}, {},
        onPlayClick = {

        }
    )
}
