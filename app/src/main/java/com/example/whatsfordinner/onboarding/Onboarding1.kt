package com.example.whatsfordinner.onboarding

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
import com.example.whatsfordinner.OnboardingButton
import com.example.whatsfordinner.R
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun Onboarding1(onContinueClicked: () -> Unit) {

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
            painter = painterResource(R.drawable.cook_book),
            contentDescription = "Cook book"
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Discover new recipes",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Browse hundreds of recipes,\n" +
                    "save your favorites and plan\n" +
                    "your meals with ease.",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(100.dp))

        OnboardingButton(
            text = "Continue",
            onClick = { onContinueClicked() }
        )

    }
}