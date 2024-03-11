package com.example.bisky.ui.screen.loginScreen.siginscreen.model

import com.example.bisky.R

data class TextSigInUI(
    val borderColor: Int = R.color.gray,
    val validateMsg: Int? = null,
    val validateColor: Int = R.color.red,
    val isClearIconVisible: Boolean = true,
    val isPlaceHolderVisible: Boolean = true,
    val placeHolder: Int = R.string.empty
)
