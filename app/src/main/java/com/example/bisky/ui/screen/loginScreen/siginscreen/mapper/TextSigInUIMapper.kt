package com.example.bisky.ui.screen.loginScreen.siginscreen.mapper

import android.text.TextUtils
import android.util.Patterns
import com.example.bisky.R
import com.example.bisky.ui.screen.loginScreen.siginscreen.model.TextSigInUI
import javax.inject.Inject

class TextSigInUIMapper @Inject constructor() {
    fun passwordToTextUI(text: String): TextSigInUI {
        val isValid = text.isEmpty() || text.length > 6
        return TextSigInUI(
            borderColor = mapToBorderColor(isValid),
            validateColor = R.color.red,
            validateMsg = if (isValid) null else R.string.validPassword,
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.password_placeholder
        )
    }

    fun mailToTextUI(text: String): TextSigInUI {
        val isValid = text.isEmpty() || isValidEmail(text)
        return TextSigInUI(
            borderColor = mapToBorderColor(isValid),
            validateColor = R.color.red,
            validateMsg = if (isValid) null else R.string.validMail,
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.email_placeholder
        )
    }

    fun mapTextAssistants(textUI: TextSigInUI, text: String) = textUI.copy(
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
