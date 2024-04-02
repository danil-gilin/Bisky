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
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.clearText
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.bisky.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun BaseTextSearch(
    state: TextFieldState,
    placeHolder: String,
    modifier: Modifier,
    isPlaceHolderVisible: Boolean,
    isClearIconVisible: Boolean
) {
    val isKeyboardOpen by keyboardAsState()
    val focusManager = LocalFocusManager.current
    if (!isKeyboardOpen) focusManager.clearFocus() else focusManager.moveFocus(FocusDirection.Enter)

    ConstraintLayout(
        modifier = modifier
    ) {
        val (tvInput, ivCLose, tvPlaceHolder, divider) = createRefs()
        BasicTextField2(
            state = state,
            modifier = Modifier
                .imePadding()
                .padding(bottom = 4.dp)
                .constrainAs(tvInput) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 0.dp)
                    top.linkTo(parent.top, 12.dp)
                    end.linkTo(ivCLose.end)
                },
            textStyle = TextStyle(
                color = colorResource(id = R.color.light_200),
                fontSize = 16.sp
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
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
                start.linkTo(parent.start, 0.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                visibility = if (isPlaceHolderVisible) {
                    Visibility.Visible
                } else {
                    Visibility.Gone
                }
            },
            fontSize = 16.sp,
            color = colorResource(id = R.color.light_400)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            modifier = Modifier
                .constrainAs(ivCLose) {
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 4.dp)
                    visibility = if (isClearIconVisible) {
                        Visibility.Visible
                    } else {
                        Visibility.Gone
                    }
                }
                .noRippleClickable {
                    state.clearText()
                },
            tint = colorResource(id = R.color.bisky_100),
            contentDescription = "Clear"
        )
        Divider(
            thickness = 1.dp,
            color = colorResource(id = R.color.bisky_100),
            modifier = Modifier.constrainAs(divider){
                width = Dimension.fillToConstraints
                start.linkTo(tvInput.start)
                top.linkTo(tvInput.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true, backgroundColor = 12314)
fun BaseTextSearchInputPreview() {
    BaseTextSearch(
        state = TextFieldState("QWEQWE"),
        placeHolder = "placheHolder",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 8.dp),
        isPlaceHolderVisible = false,
        isClearIconVisible = true
    )
}
