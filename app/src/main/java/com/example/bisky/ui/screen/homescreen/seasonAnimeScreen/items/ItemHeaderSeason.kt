package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.bisky.R

@Composable
fun ItemHeaderSeason(seasonImg: Int, seasonTitle: Int) {
    ConstraintLayout(
        modifier = Modifier.height(200.dp)
    ) {
        val (img, title, box) = createRefs()

        Image(
            painter = painterResource(id = seasonImg),
            modifier = Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                }
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .constrainAs(box) {
                    top.linkTo(parent.top)
                }
                .height(200.dp)
                .fillMaxWidth()
                .alpha(0.3f)
                .background(colorResource(R.color.bisky_400))
        )
        Text(
            text = stringResource(
                id = seasonTitle
            ),
            fontSize = 48.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            color = colorResource(R.color.light_100),
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(img.top)
                    start.linkTo(img.start)
                    end.linkTo(img.end)
                    bottom.linkTo(img.bottom)
                }
        )
    }
}
