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
        data object AnimePlayer : Home("anime_player")
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
        data object AnimePlayer : Search("anime_player")
        data object Filter : Search("filter")
        data object QuickSearch : Search("quick_search")

        companion object {
            const val route = "search"
        }
    }

    sealed class Archive(route: String) : Destination(
        "${Companion.route}/$route",
        R.string.archive,
        R.drawable.ic_archive
    ) {
        data object Anime : Archive("anime")
        data object AnimePlayer : Archive("anime_player")
        data object ContainerScreen : Archive("home_screen")
        data object QuickSelectScreen : Archive("quick_select_screen")

        companion object {
            const val route = "archive"
        }
    }

    sealed class Profile(route: String) : Destination(
        "${Companion.route}/$route",
        R.string.profile,
        R.drawable.ic_profile
    ) {
        data object Anime : Profile("anime")
        data object AnimePlayer : Profile("anime_player")
        data object HomeScreen : Profile("profile_home_screen")

        companion object {
            const val route = "profile"
        }
    }

    companion object {
        fun getStartDestinationBy(position: Int): String =
            when (BottomDestination.getBy(position)) {
                BottomDestination.HOME_FEATURE -> Home.route
                BottomDestination.SEARCH_FEATURE -> Search.route
                BottomDestination.ARCHIVE_FEATURE -> Archive.route
                BottomDestination.PROFILE_FEATURE -> Profile.route
            }
    }
}