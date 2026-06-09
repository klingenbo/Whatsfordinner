package com.example.whatsfordinner.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.R

@Composable
fun MealPlanIcon(
    isInMealPlan: Boolean,
    onMealPlanClicked: () -> Unit
) {
    Image(
        painter = painterResource(
            if (isInMealPlan) R.drawable.meal_plan_filled else R.drawable.meal_plan
        ),
        contentDescription = "Outlined meal plan",
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
    )
}
