package com.example.bisky.ui.screen.homescreen.containehomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerView.Event
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.HomeTab
import com.example.bisky.ui.screen.homescreen.containehomescreen.model.HomeTabType
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.AllGenreScreen
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesScreen
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreen

@Composable
fun HomeContainerScreen(
    navController: NavController,
    homeViewModel: HomeContainerViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeContainerScreen(
        navController,
        uiState = uiState,
        onTabClick = {
            homeViewModel.onEvent(Event.OnTabSelected(it))
        }
    )
}

@Composable
fun HomeContainerScreen(
    navController: NavController,
    uiState: HomeContainerView.State,
    onTabClick: (HomeTabType) -> Unit
) {
    when (uiState.currentTabType) {
        HomeTabType.Season -> SeasonAnimeScreen(navController)
        HomeTabType.Genre -> AllGenreScreen(navController)
        HomeTabType.New -> NewSeriesScreen(navController)
    }
    TabHomeScreen(uiState, onTabClick)
}

@Composable
fun TabHomeScreen(
    uiState: HomeContainerView.State,
    onTabClick: (HomeTabType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.bisky_dark_400_alpha_80))
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(painter = painterResource(R.drawable.ic_logo), contentDescription = null)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterVertically),
            horizontalArrangement = Arrangement.Start
        ) {
            items(
                uiState.listTab,
                key = {it.name}
            ) {
                if (it.isSelected) {
                    SelectedTab(it, onTabClick)
                } else {
                    UnselectedTab(it, onTabClick)
                }
            }
        }
    }
}

@Composable
fun SelectedTab(tab: HomeTab, onTabClick: (HomeTabType) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable {
                onTabClick(tab.type)
            }
    ) {
        val (title, selected) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = stringResource(id = tab.name),
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            color = colorResource(id = R.color.light_100)
        )
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.bisky_100))
                .constrainAs(selected) {
                    width = Dimension.fillToConstraints
                    height = Dimension.value(2.dp)
                    top.linkTo(title.bottom, 2.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun UnselectedTab(tab: HomeTab, onTabClick: (HomeTabType) -> Unit) {
    Text(
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable {
                onTabClick(tab.type)
            },
        text = stringResource(id = tab.name),
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        color = colorResource(id = R.color.light_100)
    )
}

@Preview(showBackground = true)
@Composable
fun TabHomeScreenPreview() {
    TabHomeScreen(
        HomeContainerView.State(),
        onTabClick = {}
    )
}
