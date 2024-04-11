package com.example.bisky.ui.screen.searchpage.searchrootscreen.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchAnimeUI
import kotlinx.coroutines.Dispatchers

@Composable
fun SearchAnime(
    searchAnimeUI: SearchAnimeUI,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .background(color = colorResource(id = R.color.bisky_dark_400))
            .noRippleClickable { onClick(searchAnimeUI.itemId) }
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(searchAnimeUI.img)
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
            if (searchAnimeUI.isScoreVisible) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(4.dp, 4.dp, 4.dp, 4.dp)
                        .align(Alignment.TopEnd)
                        .background(
                            colorResource(searchAnimeUI.scoreColor),
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
                        text = searchAnimeUI.score,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(2.dp, 1.dp, 4.dp, 2.dp),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W700,
                        color = colorResource(R.color.light_100)
                    )
                }
            }
            Text(
                text = searchAnimeUI.status,
                fontSize = 10.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .padding(top = 2.dp, start = 4.dp, end = 4.dp, bottom = 6.dp)
                    .background(
                        color = colorResource(id = searchAnimeUI.statusColor),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(top = 1.dp, start = 4.dp, end = 4.dp, bottom = 2.dp)
                    .align(Alignment.BottomEnd),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.white)
            )
        }
        Text(
            text = searchAnimeUI.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .padding(top = 6.dp)
                .width(100.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.white)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchAnimePreview() {
    SearchAnime(
        SearchAnimeUI(
            itemId = "itemId",
            name = "anime anime anime anime anime",
            img = "",
            status = "released",
            statusColor = R.color.lime,
            score = "8.0",
            scoreColor = R.color.lime,
            isScoreVisible = true
        ),
        {}
    )
}