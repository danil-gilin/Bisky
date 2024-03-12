package com.example.bisky.ui.screen.loginScreen.sigupscreen.mapper

import android.text.TextUtils
import android.util.Patterns
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.sigupscreen.model.TextUI
import javax.inject.Inject

class TextUIMapper @Inject constructor() {

    fun loginToTextUI(text: String): TextUI {
        val isValid = true
        return TextUI(
            borderColor = mapToBorderColor(isValid),
            validateColor = R.color.red,
            validateMsg = if (isValid) null else R.string.validLogin,
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.login_placeholder
        )
    }

    fun passwordToTextUI(text: String): TextUI {
        val isValid = text.isEmpty() || text.length > 6
        return TextUI(
            borderColor = mapToBorderColor(isValid),
            validateColor = R.color.red,
            validateMsg = if (isValid) null else R.string.validPassword,
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.password_placeholder
        )
    }

    fun mailToTextUI(text: String): TextUI {
        val isValid = text.isEmpty() || isValidEmail(text)
        return TextUI(
            borderColor = mapToBorderColor(isValid),
            validateColor = R.color.red,
            validateMsg = if (isValid) null else R.string.validMail,
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.email_placeholder
        )
    }

    fun mapTextAssistants(textUI: TextUI, text: String) = textUI.copy(
        isClearIconVisible = text.isNotEmpty(),
        isPlaceHolderVisible = text.isEmpty(),
        validateMsg = if (text.isEmpty()) null else textUI.validateMsg,
        borderColor = if (text.isEmpty()) R.color.gray else textUI.borderColor
    )

    fun mapToBorderColor(isValid: Boolean) =
        if (isValid) {
            R.color.gray
        } else {
            R.color.red
        }

    fun isValidEmail(mail: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(mail)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        }
    }
}
