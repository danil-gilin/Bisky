package com.example.bisky.ui.screen.searchscreen.quicksearch.items.animeCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.searchscreen.quicksearch.model.AnimeDescriptionUI
import com.example.bisky.ui.screen.searchscreen.quicksearch.model.AnimeBackInfoUI
import kotlinx.coroutines.Dispatchers

@Composable
fun AnimeFullInfoCard(
    anime: AnimeBackInfoUI,
    onClickMoreInfo: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(top = 8.dp)
            .width(360.dp)
            .height(520.dp)
    ) {
        Row {
            BaseInfo(
                anime,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
                ,
            )
            PlusInfo(
                anime.age,
                anime.score,
                anime.scoreColor,
                anime.isScoreVisible,
                anime.isAgeVisible,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }
        if (anime.descriptionUI != null) {
            AnimeDescriptionItems(
                anime.descriptionUI,
                onClickMoreInfo
            )
        }
        ScreenshotItem(
            anime.screenshotList,
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun BaseInfo(
    anime: AnimeBackInfoUI,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextItem(
            anime.infoDuration,
            R.drawable.ic_player
        )
        TextStatusItem(
            anime.infoType,
            R.drawable.ic_clock,
            anime.infoStatus,
            anime.statusColor
        )
        TextItem(
            anime.infoDate,
            R.drawable.ic_calendar
        )
        TextItem(
            anime.infoGenre,
            R.drawable.ic_genre
        )
        TextItem(
            anime.infoProducer,
            R.drawable.ic_paint
        )
    }
}


@Composable
private fun PlusInfo(
    age: String,
    score: String,
    scoreColor: Int,
    isScoreVisible: Boolean,
    ageVisible: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (isScoreVisible) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp, 4.dp, 4.dp, 2.dp)
                    .background(
                        colorResource(scoreColor),
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
                    text = score,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(2.dp, 1.dp, 4.dp, 2.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W700,
                    color = colorResource(R.color.light_100)
                )
            }
        }

        if (ageVisible) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp, 2.dp, 4.dp, 4.dp)
                    .background(
                        colorResource(R.color.light_200),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .align(Alignment.End)
            ) {
                Text(
                    text = age,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(2.dp, 1.dp, 2.dp, 1.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W700,
                    color = colorResource(R.color.black)
                )
            }
        }
    }
}

@Composable
private fun TextItem(text: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp)
                .height(12.dp)
                .width(12.dp),
            tint = colorResource(id = R.color.light_100),
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_400)
        )
    }
}

@Composable
private fun TextStatusItem(
    text: String,
    icon: Int,
    statusText: String,
    colorStatus: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp,8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp)
                .height(12.dp)
                .width(12.dp),
            tint = colorResource(id = R.color.light_100),
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 0.dp, 0.dp),
            fontFamily = FontFamily.Default,
            letterSpacing = (-0.02).sp,
            fontWeight = FontWeight.W700,
            color = colorResource(R.color.light_400)
        )
        Text(
            text = statusText,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(4.dp, 0.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Default,
            letterSpacing = (-0.02).sp,
            fontWeight = FontWeight.W900,
            color = colorResource(colorStatus)
        )
    }
}

@Composable
fun AnimeDescriptionItems(
    animeDescriptionUI: AnimeDescriptionUI,
    onClickMoreInfo: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = colorResource(id = R.color.bisky_dark_400))
            .padding(start = 8.dp, end = 8.dp)
    ) {
        val text = if (animeDescriptionUI.isFullInfo) {
            animeDescriptionUI.text
        } else {
            animeDescriptionUI.text.substring(0, 250) + "..."
        }
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.light_300)
        )
        if (!animeDescriptionUI.isFullInfo) {
            Text(
                text = stringResource(id = R.string.anime_full_description),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .noRippleClickable { onClickMoreInfo(true) },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.light_400)
            )
        } else {
            Text(
                text = stringResource(id = R.string.anime_small_description),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .noRippleClickable { onClickMoreInfo(false) },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.light_400)
            )
        }
    }
}

@Composable
fun ScreenshotItem(
    list: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = list){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .fetcherDispatcher(Dispatchers.IO)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(165.dp)
                    .width(300.dp)
                    .padding(bottom = 16.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                placeholder = painterResource(id = R.drawable.ic_logo),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeFullInfoCardPreview() {
    AnimeFullInfoCard(
        AnimeBackInfoUI.previewItemAnimeBackInfo,
        onClickMoreInfo = {}
    )
}