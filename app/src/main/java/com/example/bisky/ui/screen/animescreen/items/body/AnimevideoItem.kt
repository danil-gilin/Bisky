package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.animescreen.model.body.AnimeVideoUI

@Composable
fun AnimeVideoItem(
    animeVideoUI: AnimeVideoUI
) {
    val uriHandler = LocalUriHandler.current
    Column {
        Text(
            text = stringResource(id = R.string.anime_video_title),
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
            items(items = animeVideoUI.list) {
                Box(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .noRippleClickable {
                            uriHandler.openUri(it)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(300.dp)
                            .height(150.dp)
                            .noRippleClickable {
                                uriHandler.openUri(it)
                            }
                            .background(color = colorResource(id = R.color.bisky_dark_200))
                    )
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(150.dp)
                            .background(colorResource(id = R.color.bisky_dark_400_alpha_80))
                    )
                    Text(
                        text = stringResource(id = R.string.anime_video),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.light_100)
                    )
                }
            }
        }
    }
}

@Composable()
@Preview(showBackground = true)
fun AnimeVideoItemPreview() {
    AnimeVideoItem(
        AnimeVideoUI(
            itemId = "rea",
            listOf("hello", "gfds", "fdsfsd")
        )
    )
}