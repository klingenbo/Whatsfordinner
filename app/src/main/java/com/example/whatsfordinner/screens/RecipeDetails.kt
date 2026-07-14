package com.example.whatsfordinner.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.whatsfordinner.R
import com.example.whatsfordinner.Recipe
import com.example.whatsfordinner.SavedRecipesViewModel
import com.example.whatsfordinner.components.FavoritesIcon
import com.example.whatsfordinner.components.MealPlanIcon
import com.example.whatsfordinner.components.TopBar
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun RecipeDetails(
    onBackClicked: () -> Unit,
    savedViewModel: SavedRecipesViewModel,
    recipe: Recipe?
) {
    Scaffold(
        topBar = {
            TopBar(
                onBack = onBackClicked
            )
        }
    ) { innerPadding ->

        val recipe = recipe ?: return@Scaffold
        val favorites by savedViewModel.favorites.collectAsState()
        val mealPlan by savedViewModel.mealPlan.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Sage50)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val isFavorite = favorites.any { it.id == recipe.id }
            val isInMealPlan = mealPlan.any { it.id == recipe.id }

            Spacer(modifier = Modifier.height(80.dp))

            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.name,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 40.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FavoritesIcon(
                    isFavorite = isFavorite,
                    onStarClicked = {
                        savedViewModel.toggleFavorite(recipe)
                    }
                )

                Spacer(modifier = Modifier.width(40.dp))

                MealPlanIcon(
                    isInMealPlan = isInMealPlan,
                    onMealPlanClicked = {
                        savedViewModel.toggleMealPlan(recipe)
                    }
                )
            }

            Text(
                text = recipe.details,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 30.dp)
            )
        }
    }
}