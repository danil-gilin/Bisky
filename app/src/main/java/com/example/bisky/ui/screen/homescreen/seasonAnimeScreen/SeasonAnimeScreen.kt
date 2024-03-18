package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreenView.Event
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.AnimeSeasonUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun SeasonAnimeScreen(
    viewModel: SeasonAnimeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SeasonAnimeScreen(
        uiState = uiState,
        onScrollItem = {
            viewModel.onEvent(Event.OnScrollItem(it))
        }
    )
}

@Composable
fun SeasonAnimeScreen(
    uiState: SeasonAnimeScreenView.State,
    onScrollItem: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = uiState.positionScroll)
    val listAnime = uiState.itemsAnime

    LaunchedEffect(lazyListState) {
        snapshotFlow {
            lazyListState.firstVisibleItemIndex
        }
            .debounce(200L)
            .collectLatest {
                onScrollItem(it)
            }
    }
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 0.dp, top = 45.dp, 0.dp, 0.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.bisky_dark_400))
    ) {
        item {
            ConstraintLayout(
                modifier = Modifier.height(200.dp)
            ) {
                val (img, title, box) = createRefs()

                Image(
                    painter = painterResource(id = uiState.seasonImg),
                    modifier = Modifier
                        .constrainAs(img) {
                            top.linkTo(parent.top)
                        }
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .constrainAs(box) {
                            top.linkTo(parent.top)
                        }
                        .height(200.dp)
                        .fillMaxWidth()
                        .alpha(0.3f)
                        .background(colorResource(R.color.bisky_400))
                )
                Text(
                    text = stringResource(
                        id = uiState.seasonTitle
                    ),
                    fontSize = 48.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W700,
                    color = colorResource(R.color.light_100),
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(img.top)
                            start.linkTo(img.start)
                            end.linkTo(img.end)
                            bottom.linkTo(img.bottom)
                        }
                )
            }
        }
        items(
            listAnime,
            key = { it.itemId }
        )
        { item ->
            ItemAnimeSeason(item)
        }
        item {
            if (uiState.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(20.dp)
                            .align(Alignment.CenterHorizontally)
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ItemAnimeSeason(seasonUI: AnimeSeasonUI) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp, 0.dp, 14.dp, 0.dp)
    ) {
        val (imgBackground, title, box, imgPoster, description, rating, genre) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(seasonUI.backgroundImg)
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .constrainAs(imgBackground) {
                    top.linkTo(parent.top)
                }
                .clip(RoundedCornerShape(8.dp))
                .height(200.dp)
                .fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .constrainAs(box) {
                    top.linkTo(parent.top)
                }
                .height(200.dp)
                .fillMaxWidth()
                .alpha(0.6f)
                .background(colorResource(R.color.bisky_dark_400))
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(seasonUI.img)
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .constrainAs(imgPoster) {
                    top.linkTo(imgBackground.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .clip(RoundedCornerShape(3.dp))
                .height(110.dp)
                .width(80.dp),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = seasonUI.title,
            fontSize = 13.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            lineHeight = 14.sp,
            color = colorResource(R.color.light_100),
            modifier = Modifier
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(imgPoster.top)
                    start.linkTo(imgPoster.end, 16.dp)
                    if (seasonUI.isRatingVisible) {
                        end.linkTo(rating.start, 16.dp, 16.dp)
                    } else {
                        end.linkTo(parent.end, 16.dp, 16.dp)
                    }
                }
        )
        Text(
            text = seasonUI.description,
            fontSize = 13.sp,
            lineHeight = 14.sp,
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            color = colorResource(R.color.light_300),
            modifier = Modifier
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(title.bottom, 12.dp)
                    end.linkTo(imgBackground.end, 8.dp)
                    start.linkTo(title.start)
                    bottom.linkTo(genre.top, 8.dp)
                }
                .alpha(0.8f)
        )
        if (seasonUI.isRatingVisible) {
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .constrainAs(rating) {
                        top.linkTo(imgBackground.top, 8.dp)
                        end.linkTo(imgBackground.end, 8.dp)
                    }
                    .background(
                        colorResource(seasonUI.ratingColor),
                        shape = RoundedCornerShape(6.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    modifier = Modifier
                        .padding(4.dp, 4.dp, 0.dp, 4.dp)
                        .height(8.dp)
                        .width(8.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    text = seasonUI.rating,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(2.dp, 4.dp, 6.dp, 4.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W700,
                    color = colorResource(R.color.light_100)
                )
            }
        }
        Text(
            text = seasonUI.genre,
            fontSize = 13.sp,
            modifier = Modifier
                .constrainAs(genre) {
                    width = Dimension.fillToConstraints
                    start.linkTo(imgPoster.start)
                    end.linkTo(imgBackground.end, 8.dp)
                    bottom.linkTo(imgBackground.bottom, 8.dp)
                },
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = colorResource(R.color.light_100)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemAnimeSeasonPreview() {
    ItemAnimeSeason(
        AnimeSeasonUI(
            itemId = "2",
            img = "R.drawable.anime_autumn",
            title = "anime anime anime",
            description = "anime anime anime",
            rating = "0.0",
            ratingColor = R.color.bisky_200,
            isRatingVisible = true,
            genre = "anime / anime / anime",
            backgroundImg = "R.drawable.anime_autumn"
        )
    )
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
        {}
    )
}
