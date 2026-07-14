package com.example.whatsfordinner.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.R

@Composable
fun FavoritesIcon(
    isFavorite: Boolean,
    onStarClicked: () -> Unit
) {

    Image(
        painter = painterResource(
            if (isFavorite) R.drawable.favorite_yellow else R.drawable.favorite_black
        ),
        contentDescription = "Star",
        modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .padding(end = 8.dp)
            .clickable { onStarClicked() },
    )
}