package com.example.bisky.ui.screen.searchscreen.quicksearch.model

import com.example.bisky.R

data class ControlButtonUI(
    val count: String,
    val isBackVisible: Boolean,
    val isCountVisible: Boolean,
    val backGroundLikeColor: Int,
    val backGroundDislikeColor: Int,
    val likeColor: Int,
    val dislikeColor: Int,
) {
    companion object {
        val preview = ControlButtonUI(
            count = "5",
            isBackVisible = true,
            isCountVisible = true,
            backGroundLikeColor = R.color.bisky_dark_200_alpha_60,
            backGroundDislikeColor = R.color.bisky_dark_200_alpha_60,
            likeColor = R.color.bisky_300,
            dislikeColor = R.color.bisky_300,
        )

        val state = ControlButtonUI(
            count = "0",
            isBackVisible = false,
            isCountVisible = false,
            backGroundLikeColor = R.color.bisky_dark_200_alpha_60,
            backGroundDislikeColor = R.color.bisky_dark_200_alpha_60,
            likeColor = R.color.bisky_300,
            dislikeColor = R.color.bisky_300,
        )
    }
}
