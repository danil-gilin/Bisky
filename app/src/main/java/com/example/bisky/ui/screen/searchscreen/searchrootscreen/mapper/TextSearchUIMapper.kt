package com.example.bisky.ui.screen.searchscreen.searchrootscreen.mapper

import android.text.TextUtils
import android.util.Patterns
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.siginscreen.model.TextSigInUI
import com.example.bisky.ui.screen.searchscreen.searchrootscreen.model.SearchUI
import javax.inject.Inject

class TextSearchUIMapper@Inject constructor() {
    fun map(text: String): SearchUI {
        return SearchUI(
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.search_placeholder
        )
    }
}
