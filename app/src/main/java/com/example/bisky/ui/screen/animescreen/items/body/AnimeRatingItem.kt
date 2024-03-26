package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.example.bisky.ui.screen.animescreen.model.body.AnimeRatingUI

@Composable
fun AnimeRatingItem(
    animeRatingUI: AnimeRatingUI,
    onDeleteScoreClick: () -> Unit,
    onSelectScore: (Int) -> Unit,
    onCompleteScore: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Title(
            animeRatingUI.ratingCount,
            animeRatingUI.rating,
            animeRatingUI.ratingColor,
            animeRatingUI.isRatingVisible
        )
        if (animeRatingUI.isRatingVisibleUser) {
            UserRating(animeRatingUI.ratingUser, animeRatingUI.ratingColorUser, onDeleteScoreClick)
        } else {
            SelectStart(onSelectScore, onCompleteScore, animeRatingUI.selectedScore)
        }
    }
}

@Composable
private fun UserRating(
    score: String,
    scoreColor: Int,
    onDeleteScoreClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(16.dp, 0.dp, 4.dp, 0.dp)
    ) {
        Text(
            text = stringResource(id = R.string.anime_user_rating_title),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(top = 4.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_300)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp, 4.dp, 4.dp, 0.dp)
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
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(2.dp, 1.dp, 4.dp, 2.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W700,
                color = colorResource(R.color.light_100)
            )
        }

        Text(
            text = stringResource(id = R.string.delete),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(bottom = 2.dp, start = 4.dp)
                .noRippleClickable { onDeleteScoreClick() },
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W400,
            color = colorResource(R.color.light_300)
        )
    }
}

@Composable
private fun SelectStart(
    onSelectScore: (Int) -> Unit,
    onCompleteScore: () -> Unit,
    selectedScore: Int
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(10) {
                val color = if (it <= selectedScore) R.color.bisky_100 else R.color.bisky_dark_100
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    modifier = Modifier
                        .padding(2.dp, 4.dp, 4.dp, 4.dp)
                        .height(25.dp)
                        .width(25.dp)
                        .noRippleClickable {
                            onSelectScore(it)
                        },
                    tint = colorResource(id = color),
                    contentDescription = null
                )
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            enabled = selectedScore >= 0,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = colorResource(R.color.bisky_200),
                    disabledContainerColor = colorResource(R.color.bisky_400)
                ),
            onClick = { onCompleteScore() }
        ) {
            Text(
                text = stringResource(id = R.string.select_rating),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                color = colorResource(R.color.light_300)
            )
        }
    }
}


@Composable
private fun Title(
    scoreCount: String,
    score: String,
    scoreColor: Int,
    ratingVisible: Boolean
) {
    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(16.dp, 0.dp, 4.dp, 0.dp)
    ) {
        Text(
            text = stringResource(id = R.string.anime_rating_title),
            fontSize = 20.sp,
            modifier = Modifier,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        if (ratingVisible) {
            Text(
                text = scoreCount,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 1.dp, start = 8.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                color = colorResource(R.color.light_300)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp, 4.dp, 4.dp, 0.dp)
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


@Composable
@Preview(showBackground = true)
fun AnimeRatingItemPreview() {
    AnimeRatingItem(
        AnimeRatingUI(
            itemId = "daf",
            ratingCount = "400 оценок",
            rating = "4.0",
            ratingColor = R.color.red,
            isRatingVisible = true,
            ratingUser = "2.0",
            ratingColorUser = R.color.red,
            isRatingVisibleUser = false,
            selectedScore = -1
        ),
        {},
        {},
        {}
    )
}