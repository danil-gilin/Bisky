package com.example.bisky.ui.screen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.model.AnimeSeasonUI

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState
    )
}

@Composable
fun HomeScreen(
    uiState: HomeScreenView.State
) {
    val listAnime = uiState.itemsAnime
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.bisky_dark_400))
    ) {
        val (img, title, box, list) = createRefs()

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
        Box(modifier = Modifier
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
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(list) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(img.bottom, 8.dp)
                    start.linkTo(img.start, 8.dp)
                    end.linkTo(img.end, 8.dp)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(listAnime) { item ->
                ItemAnimeSeason(item)
            }
        }
    }
}


@Composable
fun ItemAnimeSeason(seasonUI: AnimeSeasonUI) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (imgBackground, title, box, imgPoster, description, rating, genre) = createRefs()

        AsyncImage(
            model = seasonUI.backgroundImg,
            modifier = Modifier
                .constrainAs(imgBackground) {
                    top.linkTo(parent.top)
                }
                .clip(RoundedCornerShape(8.dp))
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(modifier = Modifier
            .constrainAs(box) {
                top.linkTo(parent.top)
            }
            .height(200.dp)
            .fillMaxWidth()
            .alpha(0.6f)
            .background(colorResource(R.color.bisky_dark_400))
        )
        AsyncImage(
            model = seasonUI.img,
            modifier = Modifier
                .constrainAs(imgPoster) {
                    top.linkTo(imgBackground.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .clip(RoundedCornerShape(3.dp))
                .height(110.dp)
                .width(80.dp),
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
        if(seasonUI.isRatingVisible) {
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
                    color = colorResource(R.color.light_100),
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
                }
            ,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = colorResource(R.color.light_100))
    }
}

@Preview(showBackground = true)
@Composable
fun ItemAnimeSeasonPreview() {
    ItemAnimeSeason(
        AnimeSeasonUI(
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
fun HomeScreenPreview() {
    HomeScreen(
        HomeScreenView.State(
            seasonImg = R.drawable.anime_autumn,
            seasonTitle = R.string.anime_autumn_title,
            itemsAnime = listOf(
                AnimeSeasonUI(
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
        )
    )
}

class Counter {
    var value = 0
}