package com.example.bisky.ui.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.bisky.R

enum class BottomDestination(@DrawableRes val icon: Int, @StringRes val nameResId: Int) {
    HOME_FEATURE(R.drawable.ic_home, R.string.home),
    SEARCH_FEATURE(R.drawable.ic_search, R.string.search);

    companion object {
        fun getBy(position: Int) =
            values().firstOrNull { it.ordinal == position } ?: HOME_FEATURE
    }
}