package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.screen.animescreen.model.body.AnimeScreenshotsUI
import kotlinx.coroutines.Dispatchers

@Composable
fun AnimeScreenshotItem(
    animeScreenshotsUI: AnimeScreenshotsUI
) {
    Column {
        Text(
            text = stringResource(id = R.string.anime_screenshots_title),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp, 0.dp, 4.dp, 16.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        LazyRow(
            contentPadding = PaddingValues(start = 16.dp)
        ) {
            items(items = animeScreenshotsUI.list){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .fetcherDispatcher(Dispatchers.IO)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .height(180.dp)
                        .width(290.dp)
                        .padding(end = 20.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    placeholder = painterResource(id = R.drawable.ic_logo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}


@Composable()
@Preview(showBackground = true)
fun AnimeScreenshotPreview() {
    AnimeScreenshotItem(
        AnimeScreenshotsUI(
            itemId = "rea",
            listOf("hello","gfds", "fdsfsd")
        )
    )
}