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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.components.OnboardingButton
import com.example.whatsfordinner.R
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun Onboarding3(
    onGetStartedClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 30.dp)
            .background(Sage50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.cooking),
            contentDescription = "Casserole",
            modifier = Modifier.padding(20.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Never wonder what's for dinner",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Create a meal plan or let the app\n" +
                    "pick a recipe for you when you\n" +
                    "can't decide.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(80.dp))

        OnboardingButton(
            text = "Get Started",
            onClick = { onGetStartedClicked() }
        )

    }
}