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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.whatsfordinner.RecipesListViewModel
import com.example.whatsfordinner.SavedRecipesViewModel
import com.example.whatsfordinner.components.FavoritesIcon
import com.example.whatsfordinner.components.MealPlanIcon
import com.example.whatsfordinner.components.TopBar
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun RecipeDetails(
    viewModel: RecipesListViewModel,
    onBackClicked: () -> Unit,
    savedViewModel: SavedRecipesViewModel
) {
    Scaffold(
        topBar = {
            TopBar(
                onBack = onBackClicked
            )
        }
    ) { innerPadding ->

        val recipe by viewModel.selectedRecipe.collectAsState()
        val currentRecipe = recipe ?: return@Scaffold
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
            val isFavorite = favorites.any { it.id == recipe?.id }
            val isInMealPlan = mealPlan.any { it.id == recipe?.id }

            Spacer(modifier = Modifier.height(80.dp))

            AsyncImage(
                model = recipe?.image ?: "N/A",
                contentDescription = recipe?.name ?: "No image",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = recipe?.name ?: "No title",
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
                    onStarClicked = { savedViewModel.toggleFavorite(currentRecipe) }
                )

                Spacer(modifier = Modifier.width(40.dp))

                MealPlanIcon(
                    isInMealPlan = isInMealPlan,
                    onMealPlanClicked = { savedViewModel.toggleMealPlan(currentRecipe) }
                )
            }

            Text(
                text = recipe?.details ?: "",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 30.dp)
            )
        }
    }
}