package com.example.bisky.ui.screen.searchscreen.quicksearch.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable

@Composable
fun ControlButton(
    onBackClick:() -> Unit,
    onLikeClick:() -> Unit,
    onDislikeClick:() -> Unit,
    count: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.bisky_dark_400))
    ) {
        val (btnLikeDislike, btnBack, txtCount) = createRefs()
        Row(
            modifier = Modifier
                .constrainAs(btnLikeDislike) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colorResource(id = R.color.bisky_dark_200_alpha_60))
                    .noRippleClickable {
                        onLikeClick()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart_60),
                    contentDescription = "close",
                    modifier = Modifier
                        .padding(24.dp)
                        .align(Alignment.Center)
                    ,
                    tint = colorResource(id = R.color.bisky_300)
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colorResource(id = R.color.bisky_dark_200_alpha_60))
                    .noRippleClickable {
                        onDislikeClick()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close_60),
                    contentDescription = "close",
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.Center)
                    ,
                    tint = colorResource(id = R.color.bisky_300)
                )
            }
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = colorResource(id = R.color.bisky_dark_200_alpha_60))
                .constrainAs(btnBack) {
                    start.linkTo(parent.start)
                    end.linkTo(btnLikeDislike.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .noRippleClickable {
                    onBackClick()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_long),
                contentDescription = "close",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
                ,
                tint = colorResource(id = R.color.bisky_300)
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = colorResource(id = R.color.bisky_dark_200_alpha_60))
                .constrainAs(txtCount) {
                    start.linkTo(btnLikeDislike.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {

            Text(
                text = count,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .padding(vertical =8.dp, horizontal = 16.dp),
                textAlign = TextAlign.Left,
                color = colorResource(id = R.color.bisky_300)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ControlButtonPreview() {
    ControlButton(
        onBackClick = {},
        onLikeClick = {},
        onDislikeClick = {},
        count = "5"
    )
}