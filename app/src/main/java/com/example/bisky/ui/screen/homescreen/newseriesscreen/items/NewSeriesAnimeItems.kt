package com.example.bisky.ui.screen.homescreen.newseriesscreen.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.homescreen.newseriesscreen.model.NewSeriesAnimeUI
import kotlinx.coroutines.Dispatchers

@Composable
fun NewSeriesAnimeItems(
    newSeriesAnime: NewSeriesAnimeUI,
    onAnimeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.bisky_dark_100_alpha_50))
            .fillMaxWidth()
            .noRippleClickable { onAnimeClick(newSeriesAnime.itemId) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(newSeriesAnime.poster)
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .height(80.dp)
                .width(55.dp)
                .clip(shape = RoundedCornerShape(8.dp))
            ,
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column {
            Text(
                text = newSeriesAnime.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier
                    .padding(top = 12.dp, start = 8.dp)
                    .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = newSeriesAnime.infoSeries,
                fontSize = 16.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(id = R.color.blue))
                    .padding(start = 8.dp, end = 8.dp, bottom = 2.dp, top = 2.dp)
                ,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewSeriesAnimeItemsPreview() {
    NewSeriesAnimeItems(NewSeriesAnimeUI.preview, {})
}