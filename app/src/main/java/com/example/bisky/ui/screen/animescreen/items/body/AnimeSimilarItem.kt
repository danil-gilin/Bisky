package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.animescreen.model.body.SimilarAnimeListUI
import com.example.bisky.ui.screen.animescreen.model.body.SimilarAnimeUI
import kotlinx.coroutines.Dispatchers

@Composable
fun AnimeSimilarItem(
    animeListUI: SimilarAnimeListUI,
    onClickAnime: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.anime_similar_title),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp, 0.dp, 4.dp, 16.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        LazyRow(
            contentPadding = PaddingValues(start = 16.dp),
            modifier = Modifier
                .background(colorResource(id = R.color.bisky_dark_400))
        ) {
            items(animeListUI.items) {
                SimilarAnimeItem(it, onClickAnime)
            }
        }
    }
}


@Composable
fun SimilarAnimeItem(
    animeeUI: SimilarAnimeUI,
    onClickAnime: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 16.dp)
            .noRippleClickable {
                onClickAnime(animeeUI.itemId)
            }
            .background(color = colorResource(id = R.color.bisky_dark_400))
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animeeUI.poster)
                    .fetcherDispatcher(Dispatchers.IO)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(150.dp)
                    .width(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                placeholder = painterResource(id = R.drawable.ic_logo),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp, 4.dp, 4.dp, 2.dp)
            ) {
                if (animeeUI.isRatingVisible) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(4.dp, 4.dp, 4.dp, 2.dp)
                            .background(
                                colorResource(animeeUI.ratingColor),
                                shape = RoundedCornerShape(6.dp)
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_star),
                            modifier = Modifier
                                .padding(2.dp, 4.dp, 0.dp, 4.dp)
                                .height(8.dp)
                                .width(8.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        Text(
                            text = animeeUI.rating,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(2.dp, 1.dp, 4.dp, 2.dp),
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W700,
                            color = colorResource(R.color.light_100)
                        )
                    }
                }
            }
        }
        Text(
            text = animeeUI.name,
            fontSize = 14.sp,
            modifier = Modifier
                .width(100.dp)
                .padding(0.dp, 4.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(R.color.light_100)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AnimeSimilarItemPreview() {
    AnimeSimilarItem(
        SimilarAnimeListUI(
            itemId = "dsad",
            items = listOf(
                SimilarAnimeUI(
                    "sda",
                    R.drawable.ic_logo,
                    "Anime",
                    "2.0",
                    ratingColor = R.color.red,
                    true
                ),
                SimilarAnimeUI(
                    "sda",
                    R.drawable.ic_logo,
                    "Anidsadasdasdasasdasdadsdasme",
                    "2.0",
                    ratingColor = R.color.red,
                    true
                ),
                SimilarAnimeUI(
                    "sda",
                    R.drawable.ic_logo,
                    "Anime",
                    "2.0",
                    ratingColor = R.color.red,
                    true
                )
            )
        ),
        {}
    )
}