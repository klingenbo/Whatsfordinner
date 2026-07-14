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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            .background(Sage50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.meal_plan_black),
            contentDescription = stringResource(R.string.meal_plan),
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .size(150.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.onboarding_3_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = stringResource(R.string.onboarding_3_description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(80.dp))

        OnboardingButton(
            text = stringResource(R.string.button_get_started),
            onClick = { onGetStartedClicked() }
        )

    }
}