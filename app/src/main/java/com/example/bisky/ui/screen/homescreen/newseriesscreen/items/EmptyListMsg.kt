package com.example.bisky.ui.screen.homescreen.newseriesscreen.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R

@Composable
fun EmptyListMsg(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.W800,
        modifier = Modifier
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.white)
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 12L)
fun EmptyListMsgPreview() {
    EmptyListMsg("В настоящий момент нет новых серий")
}