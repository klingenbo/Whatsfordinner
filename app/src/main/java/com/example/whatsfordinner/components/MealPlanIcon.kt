package com.example.whatsfordinner.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.R

@Composable
fun MealPlanIcon(
    isInMealPlan: Boolean,
    onMealPlanClicked: () -> Unit
) {
    Image(
        painter = painterResource(
            if (isInMealPlan) R.drawable.meal_plan_yellow else R.drawable.meal_plan_black
        ),
        contentDescription = stringResource(R.string.meal_plan),
        modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .clickable { onMealPlanClicked() }
    )
}
