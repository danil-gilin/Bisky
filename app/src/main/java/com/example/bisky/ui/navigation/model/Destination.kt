package com.example.bisky.ui.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.bisky.R

sealed class Destination(
    val route: String,
    @StringRes val labelId: Int,
    @DrawableRes val icon: Int,
) {
    sealed class Home(route: String) : Destination(
        "${Companion.route}/$route",
        R.string.home,
        R.drawable.ic_home
    ) {
        data object HomeScreen : Home("home_screen")
        data object SeasonAnime : Home("season_anime")
        data object Anime : Home("anime")
        data object Genre : Home("anime")

        companion object {
            const val route = "home"
        }
    }

    sealed class Search(route: String) : Destination(
        "${Companion.route}/$route",
        R.string.search,
        R.drawable.ic_search
    ) {
        data object SearchScreen : Search("search_screen")
        data object Anime : Search("anime")
        data object Filter : Search("filter")

        companion object {
            const val route = "search"
        }
    }

    companion object {
        fun getStartDestinationBy(position: Int): String =
            when (BottomDestination.getBy(position)) {
                BottomDestination.HOME_FEATURE -> Home.route
                BottomDestination.SEARCH_FEATURE -> Search.route
            }
    }
}