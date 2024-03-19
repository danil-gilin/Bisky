package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.model.GenreUI
import kotlinx.coroutines.Dispatchers

@Composable
fun ItemGenre(
    genreUI: GenreUI
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        val (imgBackground, title, box, imgBackground1, description, imgBackground2) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(genreUI.posters.get(0))
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .constrainAs(imgBackground) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .height(200.dp)
                .width(150.dp)
                .blur(
                    radiusX = 2.dp,
                    radiusY = 2.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(genreUI.posters.get(1))
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .constrainAs(imgBackground1) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 120.dp)
                    top.linkTo(parent.top, (-30).dp)
                    bottom.linkTo(parent.bottom)
                }
                .height(250.dp)
                .width(150.dp)
                .blur(
                    radiusX = 2.dp,
                    radiusY = 2.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(genreUI.posters.get(2))
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .constrainAs(imgBackground2) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 250.dp)
                    end.linkTo(parent.end)
                }
                .blur(
                    radiusX = 2.dp,
                    radiusY = 2.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .constrainAs(box) {
                    top.linkTo(parent.top)
                }
                .height(200.dp)
                .fillMaxWidth()
                .alpha(0.6f)
                .background(colorResource(R.color.bisky_dark_400))
        )
        Text(
            text = genreUI.name,
            fontSize = 25.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            lineHeight = 25.sp,
            color = colorResource(R.color.light_100),
            modifier = Modifier
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 8.dp, 8.dp)
                }
        )
        Text(
            text = genreUI.description,
            fontSize = 13.sp,
            lineHeight = 14.sp,
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            color = colorResource(R.color.light_300),
            modifier = Modifier
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(title.bottom, 12.dp)
                    end.linkTo(imgBackground.end, 8.dp)
                    start.linkTo(title.start)
                    bottom.linkTo(parent.bottom, 8.dp)
                }
                .alpha(0.8f)
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 999999L)
fun ItemGenrePreview() {
    ItemGenre(
        GenreUI(
            itemId = "itemId",
            description = "description",
            name = "name",
            posters = listOf("dsa", "dsa", "dsa")
        )
    )
}
