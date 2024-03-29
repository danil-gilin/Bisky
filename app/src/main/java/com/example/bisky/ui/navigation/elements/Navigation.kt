package com.example.bisky.ui.navigation.elements

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.bisky.R
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.navigation.ext.homeNavGraph
import com.example.bisky.ui.navigation.model.BottomDestination
import com.example.bisky.ui.navigation.ext.rememberBottomBar
import com.example.bisky.ui.navigation.ext.searchNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation() {
    val bottomBar = rememberBottomBar()

    Scaffold(bottomBar = { bottomBar.createNavComponent() }) {
        HorizontalPager(
            state = bottomBar.pagerState,
            userScrollEnabled = false
        ) { page ->
            val bottomDestination = BottomDestination.getBy(page)
            val navController =
                bottomBar.getNavController(bottomDestination) ?: return@HorizontalPager
            NavHost(
                navController = navController,
                startDestination = Destination.getStartDestinationBy(page),
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ) {

                homeNavGraph(navController = navController)
                searchNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun BottomBar(
    destinations: List<Destination>,
    selectedPage: MutableIntState,
    navController: NavController,
) {
    NavigationBar(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
        ,
        containerColor = colorResource(id = R.color.background_bottom_nav)
    ) {
        destinations.forEachIndexed { index, item ->
            val isSelected = index == selectedPage.intValue
            NavigationBarItem(
                modifier = Modifier.background(Color.Transparent),
                selected = isSelected,
                onClick = {
                    selectedPage.intValue = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.labelId)
                    )
                },
                label = {
                    Text(text = stringResource(id = item.labelId))
                }
            )
        }
    }
}



@Composable
fun RowScope.AddItem(
    currentDestination: NavDestination?,
    navController: NavHostController
) {

}
