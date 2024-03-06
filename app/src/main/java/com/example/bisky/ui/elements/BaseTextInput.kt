package com.example.bisky.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.TextFieldDecorator
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.clearText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.bisky.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BaseTextInputWith(
    text: TextFieldState,
    placeHolder: String,
    modifier: Modifier,
    colorBorder: Int = R.color.gray,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val focusManager = LocalFocusManager.current
    ConstraintLayout(
        modifier = modifier
            .border(
            BorderStroke(1.dp, colorResource(id = colorBorder)),
            shape = RoundedCornerShape(5.dp)
        )
    ) {
        val (tvInput, ivCLose) = createRefs()
        BasicTextField2(
            state = text,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .constrainAs(tvInput) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 12.dp)
                    end.linkTo(ivCLose.end)
                    bottom.linkTo(parent.bottom)
                }
            ,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            modifier = Modifier
                .constrainAs(ivCLose) {
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                    if (text.text.isEmpty()) {
                        visibility = Visibility.Gone
                    }
                }
                .clickable {
                    text.clearText()
                },
            contentDescription = "Clear"
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun BaseTextInputWithPreview() {
    BaseTextInputWith(
        text = TextFieldState(""),
        placeHolder = "placheHolder",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .border(
                BorderStroke(1.dp, colorResource(id = R.color.gray)),
                shape = RoundedCornerShape(5.dp)
            )
    )
}