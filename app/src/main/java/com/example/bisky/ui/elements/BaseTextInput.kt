package com.example.bisky.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.CodepointTransformation
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.clearText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.bisky.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun BaseTextInput(
    state: TextFieldState,
    placeHolder: String,
    modifier: Modifier,
    colorBorder: Int = R.color.gray,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPlaceHolderVisible: Boolean,
    isClearIconVisible: Boolean
) {
    val isKeyboardOpen by keyboardAsState()
    val focusManager = LocalFocusManager.current
    if (!isKeyboardOpen) focusManager.clearFocus() else focusManager.moveFocus(FocusDirection.Enter)

    val inputTransformation = if (keyboardType == KeyboardType.Password) {
        passwordCodepointTransformation()
    } else {
        null
    }

    ConstraintLayout(
        modifier = modifier
            .border(
                BorderStroke(1.dp, colorResource(id = colorBorder)),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        val (tvInput, ivCLose, tvPlaceHolder) = createRefs()
        BasicTextField2(
            state = state,
            modifier = Modifier
                .imePadding()
                .padding(bottom = 12.dp)
                .constrainAs(tvInput) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 12.dp)
                    end.linkTo(ivCLose.end)
                },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            codepointTransformation = inputTransformation,
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Text(
            text = placeHolder,
            modifier = Modifier.constrainAs(tvPlaceHolder) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(parent.start, 16.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                visibility = if (isPlaceHolderVisible) {
                    Visibility.Visible
                } else {
                    Visibility.Gone
                }
            },
            color = colorResource(id = R.color.gray)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            modifier = Modifier
                .constrainAs(ivCLose) {
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                    visibility = if (isClearIconVisible) {
                        Visibility.Visible
                    } else {
                        Visibility.Gone
                    }
                }
                .noRippleClickable {
                    state.clearText()
                },
            contentDescription = "Clear"
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun passwordCodepointTransformation(): CodepointTransformation {
    return CodepointTransformation { codepoint, _ ->
        '*'.code
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun BaseTextInputWithPreview() {
    BaseTextInput(
        state = TextFieldState(""),
        placeHolder = "placheHolder",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .border(
                BorderStroke(1.dp, colorResource(id = R.color.gray)),
                shape = RoundedCornerShape(5.dp)
            ),
        isPlaceHolderVisible = true,
        isClearIconVisible = true
    )
}
