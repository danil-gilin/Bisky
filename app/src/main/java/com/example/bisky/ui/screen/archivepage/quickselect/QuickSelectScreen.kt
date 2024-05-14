package com.example.bisky.ui.screen.archivepage.quickselect

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.archivepage.container.item.QuickSearchSelectAnimeItem
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.Action
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.Event
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectView.State
import com.example.bisky.ui.screen.archivepage.quickselect.item.HeaderQuickSelectItem
import com.example.bisky.ui.screen.archivepage.quickselect.item.QuickSelectAnimeItem
import com.example.bisky.ui.screen.archivepage.quickselect.model.SelectAnimeUI


@Composable
fun QuickSelectScreen(
    navController: NavController,
    viewModel: QuickSelectViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    QuickSelectScreen(
        uiState,
        onLeftAnimeClick = {
            viewModel.onEvent(Event.OnLeftSelect)
        },
        onRightAnimeClick = {
            viewModel.onEvent(Event.OnRightSelect)
        },
        onBackClick = {
            viewModel.onAction(Action.ShowBottomNav)
            navController.popBackStack()
        },
        onPreviewSelectedClick = {
            viewModel.onEvent(Event.OnRightSelect)
        },
        onWatchWinnerClick = {
            viewModel.onAction(Action.ShowBottomNav)
            navController.navigate(Destination.Archive.Anime.route+"/$it") {
                navController.popBackStack(Destination.Archive.QuickSelectScreen.route + "/${uiState.collection}", true)
            }
        }
    )
}

@Composable
fun QuickSelectScreen(
    uiState: State,
    onLeftAnimeClick: () -> Unit,
    onRightAnimeClick: () -> Unit,
    onBackClick: () -> Unit,
    onPreviewSelectedClick: () -> Unit,
    onWatchWinnerClick: (String) -> Unit
) {
    BackHandler {
        onBackClick()
    }
    Column {
        HeaderQuickSelectItem(
            modifier = Modifier,
            onBackClick
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (animeLeft,
                animeRight,
                middleLine,
                ivVS,
                tvPosition,
                btnBackSelected,
                btnWatchAnime
            ) = createRefs()
            QuickSelectAnimeItem(
                uiState.leftAnimeUI,
                onLeftAnimeClick,
                modifier = Modifier.constrainAs(animeLeft) {
                    if (uiState.isWinnerScreen) {
                        if (uiState.winnerIsRight) {
                            visibility = Visibility.Gone
                        } else {
                            end.linkTo(parent.end)
                        }
                        start.linkTo(parent.start)
                    } else {
                        visibility = Visibility.Visible
                        end.linkTo(middleLine.start)
                    }
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            QuickSelectAnimeItem(
                uiState.rightAnimeUI,
                onRightAnimeClick,
                modifier = Modifier.constrainAs(animeRight) {
                    width = Dimension.fillToConstraints
                    if (uiState.isWinnerScreen) {
                        if (uiState.winnerIsRight) {
                            start.linkTo(parent.start)
                        } else {
                            visibility = Visibility.Gone
                        }
                    } else {
                        visibility = Visibility.Visible
                        start.linkTo(middleLine.end)
                    }
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Box(modifier = Modifier
                .fillMaxHeight()
                .constrainAs(middleLine) {
                    visibility = if (uiState.isWinnerScreen) {
                        Visibility.Gone
                    } else {
                        Visibility.Visible
                    }
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .width(6.dp)
                .background(colorResource(id = R.color.bisky_400))
            )
            Image(
                painter = painterResource(id = R.drawable.ic_vs),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(ivVS) {
                        visibility = if (uiState.isWinnerScreen) {
                            Visibility.Gone
                        } else {
                            Visibility.Visible
                        }
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            PositionSelected(
                uiState.allCountSelectedAnime,
                uiState.currentPositionSelectAnime,
                modifier = Modifier
                    .constrainAs(tvPosition) {
                        visibility = if (uiState.isWinnerScreen) {
                            Visibility.Gone
                        } else {
                            Visibility.Visible
                        }
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
            BackSelectButton(
                onPreviewSelectedClick,
                modifier = Modifier
                    .constrainAs(btnBackSelected) {
                        visibility = if (uiState.isWinnerScreen) {
                            Visibility.Gone
                        } else {
                            Visibility.Visible
                        }
                        start.linkTo(parent.start, 16.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                    }
            )
            if (uiState.isWinnerScreen) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(80.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        colorResource(id = R.color.transparent),
                                        colorResource(id = R.color.bisky_100_alpha_20),
                                        colorResource(id = R.color.bisky_100_alpha_30),
                                        colorResource(id = R.color.bisky_100_alpha_20),
                                        colorResource(id = R.color.transparent),
                                    )
                                )
                            )
                            .blur(
                                radiusX = 5.dp,
                                radiusY = 5.dp,
                                edgeTreatment = BlurredEdgeTreatment.Unbounded
                            )
                    )
                    Text(
                        text = stringResource(R.string.winner),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.W800,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.light_100)
                    )
                }
            }
            Text(
                text = stringResource(R.string.watch_winner),
                fontSize = 32.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.bisky_dark_200))
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .noRippleClickable {
                        onWatchWinnerClick(uiState.winner.itemId)
                    }
                    .constrainAs(btnWatchAnime) {
                        visibility = if (uiState.isWinnerScreen) {
                            Visibility.Visible
                        } else {
                            Visibility.Gone
                        }
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, 64.dp)
                    }
                ,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.light_100)
            )
        }
    }
}

@Composable
private fun BackSelectButton(
    onBtnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = colorResource(id = R.color.bisky_dark_200))
            .noRippleClickable {
                onBtnClick()
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
}

@Composable
private fun PositionSelected(
    allCountSelectedAnime: Int,
    currentPositionSelectAnime: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .clip(RoundedCornerShape(16.dp, 16.dp))
            .background(colorResource(id = R.color.bisky_dark_200))
            .padding(top = 18.dp, bottom = 18.dp, start = 12.dp, end = 12.dp),
    ) {
        Text(
            text = currentPositionSelectAnime.toString(),
            fontSize = 38.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier,
            maxLines = 1,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.bisky_100)
        )
        Text(
            text = "/",
            fontSize = 38.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier
                .padding(start = 4.dp),
            maxLines = 1,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.white)
        )
        Text(
            text = allCountSelectedAnime.toString(),
            fontSize = 38.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier
                .padding(start = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.white)
        )
    }
}


@Preview
@Composable
fun QuickSelectScreenPreview() {
    QuickSelectScreen(
        State(
            leftAnimeUI = SelectAnimeUI.default,
            rightAnimeUI = SelectAnimeUI.default,
            isWinnerScreen = true
        ),
        {},
        {},
        {},
        {},
        {}
    )
}

@Preview
@Composable
fun PositionSelectedPreview() {
    PositionSelected(
        0,
        72
    )
}