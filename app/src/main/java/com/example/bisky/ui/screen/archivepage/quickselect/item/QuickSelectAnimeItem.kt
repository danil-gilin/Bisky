package com.example.bisky.ui.screen.archivepage.quickselect.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.archivepage.quickselect.model.SelectAnimeUI
import kotlinx.coroutines.Dispatchers

@Composable
fun QuickSelectAnimeItem(
    selectAnimeUI: SelectAnimeUI,
    onClickAnime: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier =
        modifier
            .fillMaxHeight()
            .noRippleClickable { onClickAnime() }
    ) {
        AsyncImage(
            model = selectAnimeUI.backGroundImg,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .blur(
                    radiusX = 5.dp,
                    radiusY = 5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            placeholder = painterResource(id = R.drawable.anime_spring),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .alpha(0.6f)
                .background(colorResource(R.color.bisky_dark_400))
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.bisky_dark_300))
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(200.dp)
                    .height(290.dp)
            ) {
                AsyncImage(
                    model = selectAnimeUI.poster,
                    modifier = Modifier
                        .height(235.dp)
                        .width(152.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    placeholder = painterResource(id = R.drawable.ic_logo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    text = selectAnimeUI.name,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(12.dp, 0.dp, 12.dp, 12.dp),
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W700,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    letterSpacing = (-0.02).sp,
                    color = colorResource(R.color.light_100)
                )
            }
            InfoAnime(
                selectAnimeUI,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(top = 4.dp)
                    .background(colorResource(id = R.color.bisky_dark_300))
            )
        }
    }
}

@Composable
private fun InfoAnime(
    selectAnimeUI: SelectAnimeUI,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        TextItem(selectAnimeUI.infoDuration, R.drawable.ic_player)
        TextStatusItem(
            selectAnimeUI.infoType,
            R.drawable.ic_clock,
            selectAnimeUI.status,
            selectAnimeUI.statusColor
        )
    }
}

@Composable
private fun TextItem(text: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp)
                .height(12.dp)
                .width(12.dp),
            tint = colorResource(id = R.color.light_100),
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 10.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_400)
        )
    }
}

@Composable
private fun TextStatusItem(
    text: String,
    icon: Int,
    statusText: String,
    colorStatus: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp)
                .height(12.dp)
                .width(12.dp),
            tint = colorResource(id = R.color.light_100),
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 10.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 0.dp, 0.dp),
            fontFamily = FontFamily.Default,
            letterSpacing = (-0.02).sp,
            fontWeight = FontWeight.W700,
            color = colorResource(R.color.light_400)
        )
        Text(
            text = statusText,
            fontSize = 10.sp,
            modifier = Modifier
                .padding(4.dp, 0.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Default,
            letterSpacing = (-0.02).sp,
            fontWeight = FontWeight.W900,
            color = colorResource(colorStatus)
        )
    }
}


@Composable()
@Preview(showBackground = true)
fun QuickSelectAnimeItemPreview() {
    QuickSelectAnimeItem(SelectAnimeUI.default, onClickAnime =  {})
}