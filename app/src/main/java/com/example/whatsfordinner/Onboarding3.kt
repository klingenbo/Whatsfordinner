package com.example.whatsfordinner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun Onboarding3() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 30.dp)
            .background(Sage50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.cooking),
            contentDescription = "Casserole"
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Never wonder what's for dinner",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Create a meal plan or let the app\n" +
                    "pick a recipe for you when you\n" +
                    "can't decide.",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(80.dp))

        FilledTonalButton(
            onClick = { },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.headlineSmall
            )
        }

    }
}