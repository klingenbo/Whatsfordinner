package com.example.whatsfordinner.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.R
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
@Preview
fun RecipeListError() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sage50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.computer_error),
            contentDescription = "Computer error",
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .size(300.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Unable to load recipes",
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
    }
}