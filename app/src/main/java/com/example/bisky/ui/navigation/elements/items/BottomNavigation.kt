package com.example.bisky.ui.navigation.elements.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.navigation.model.BottomDestination

@Composable
fun BottomNavigation(selectedPage: Int, onClick: (Int) -> Unit) {
    NavigationBar(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)),
        containerColor = colorResource(id = R.color.background_bottom_nav),
        tonalElevation = 0.dp
    ) {
        BottomDestination.entries.forEachIndexed { index, item ->
            val isSelected = index == selectedPage
            val tint = if (isSelected) R.color.bisky_100 else R.color.light_400
            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
                    .align(Alignment.CenterVertically)
                    .noRippleClickable {
                    if (!isSelected) {
                        onClick(index)
                    }    
                },
                painter = painterResource(id = item.icon),
                contentDescription = stringResource(id = item.nameResId),
                tint = colorResource(id = tint),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomNavigationPreview() {
    BottomNavigation(0, {})
}