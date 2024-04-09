package com.example.bisky.ui.screen.animescreen.items.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.domain.repository.anime.model.Collection
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.animescreen.model.header.AnimeCardFullInfoUI
import com.example.bisky.ui.screen.animescreen.model.header.HeaderItemUI
import com.example.bisky.ui.screen.animescreen.model.header.InfoAnimeItemUI
import kotlinx.coroutines.Dispatchers

@Composable
fun HeaderAnimeItem(
    headerItemUI: HeaderItemUI,
    onCollectionSelected: (Collection) -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(headerItemUI.screenshot)
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .blur(
                    radiusX = 2.dp,
                    radiusY = 2.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.bisky_dark_400_alpha_20),
                            colorResource(id = R.color.bisky_dark_400)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp, start = 38.dp, end = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimeCardItem(headerItemUI.animeCard)
            Text(
                text = headerItemUI.name,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white)
            )
            InfoAnimeItem(headerItemUI.infoAnimeItemUI, onCollectionSelected)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, end = 0.dp, top = 12.dp)
                .noRippleClickable {
                    onBackClick()
                }
            ,
            tint = colorResource(id = R.color.white)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderAnimeItemPreview() {
    HeaderAnimeItem(
        HeaderItemUI(
            itemId = "itemId",
            name = "anime anime anime anime anime",
            screenshot = "screnshot",
            infoAnimeItemUI = InfoAnimeItemUI(
                itemId = "info",
                infoDuration = "24 эп. по ~ 24 мин.",
                infoStatus = "вышел",
                statusColor = R.color.lime,
                infoType = "Сериал,",
                infoDate = "Осень 2012 г.",
                imgCollectionAdded = R.drawable.ic_added_collection,
                imgCollectionCompleted = R.drawable.ic_completed_collection_disable,
                imgCollectionDropped = R.drawable.ic_delete_collection_disable,
                imgCollectionWatching = R.drawable.ic_play_collection_disable,
                imgCollectionNone = R.drawable.ic_none_collection_disable
            ),
            animeCard = AnimeCardFullInfoUI(
                itemId = "itemId",
                poster = "",
                score = "8.0",
                scoreColor = R.color.lime,
                isScoreVisible = true,
                age = "16+",
                ageVisible = true
            )
        ),
        {},
        {}
    )
}
