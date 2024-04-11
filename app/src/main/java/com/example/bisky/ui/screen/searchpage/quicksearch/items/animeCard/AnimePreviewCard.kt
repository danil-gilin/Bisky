package com.example.bisky.ui.screen.searchpage.quicksearch.items.animeCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeFrontInfoUI
import kotlinx.coroutines.Dispatchers

@Composable
fun AnimePreviewCard(
    anime: AnimeFrontInfoUI,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.bisky_dark_300))
            .padding(top = 8.dp)
            .width(360.dp)
            .height(520.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.logo)
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .height(435.dp)
                .width(280.dp)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .clip(shape = RoundedCornerShape(30.dp)),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = anime.name,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(24.dp, 0.dp, 24.dp, 12.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AnimePreviewCardPreview() {
    AnimePreviewCard(
        AnimeFrontInfoUI.previewItemAnimeFront
    )
}