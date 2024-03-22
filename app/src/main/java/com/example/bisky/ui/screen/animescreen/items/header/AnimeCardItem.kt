package com.example.bisky.ui.screen.animescreen.items.header

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.screen.animescreen.model.header.AnimeCardFullInfoUI
import kotlinx.coroutines.Dispatchers

@Composable
fun AnimeCardItem(
    animeeUI: AnimeCardFullInfoUI
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
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
                    .height(230.dp)
                    .width(150.dp)
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
                if (animeeUI.isScoreVisible) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(4.dp, 4.dp, 4.dp, 2.dp)
                            .background(
                                colorResource(animeeUI.scoreColor),
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
                            text = animeeUI.score,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(2.dp, 1.dp, 4.dp, 2.dp),
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W700,
                            color = colorResource(R.color.light_100)
                        )
                    }
                }

                if (animeeUI.ageVisible) {
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
                            text = animeeUI.age,
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
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeCardItemPreview() {
    AnimeCardItem(
        AnimeCardFullInfoUI(
            itemId = "itemId",
            poster = "",
            score = "8.0",
            scoreColor = R.color.lime,
            isScoreVisible = true,
            age = "16+",
            ageVisible = true
        )
    )
}
