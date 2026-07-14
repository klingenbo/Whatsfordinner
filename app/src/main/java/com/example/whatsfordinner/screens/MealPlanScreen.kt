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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.whatsfordinner.R
import com.example.whatsfordinner.Recipe
import com.example.whatsfordinner.RecipesListViewModel
import com.example.whatsfordinner.SavedRecipesViewModel
import com.example.whatsfordinner.components.FavoritesIcon
import com.example.whatsfordinner.components.MealPlanIcon
import com.example.whatsfordinner.components.TopBar
import com.example.whatsfordinner.room.SavedRecipe
import com.example.whatsfordinner.toRecipe
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun MealPlanListScreen(
    savedViewModel: SavedRecipesViewModel,
    onDetailsClicked: (Recipe) -> Unit,
    onRandomClick: () -> Unit
) {

    val mealPlan by savedViewModel.mealPlan.collectAsState()

    when {
        mealPlan.isEmpty() -> {
            MealPlanEmptyScreen()
        }

        else ->
            MealPlanListContent(
                recipes = mealPlan,
                onDetailsClicked = onDetailsClicked,
                savedViewModel = savedViewModel,
                onRandomClick = onRandomClick
            )
    }
}

@Composable
fun MealPlanListContent(
    recipes: List<SavedRecipe>,
    onDetailsClicked: (Recipe) -> Unit,
    savedViewModel: SavedRecipesViewModel,
    onRandomClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(onRandomClick = onRandomClick)
        }
    ) { innerPadding ->

        val favorites by savedViewModel.favorites.collectAsState()
        val mealPlan by savedViewModel.mealPlan.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Sage50)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.meal_plan),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recipes) { recipe ->
                    val isFavorite = favorites.any { it.id == recipe.id }
                    val isInMealPlan = mealPlan.any { it.id == recipe.id }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Sage50)
                            .padding(20.dp, 5.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = recipe.image,
                                contentDescription = recipe.name,
                                modifier = Modifier
                                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp)
                                    .width(50.dp)
                                    .height(50.dp),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = recipe.name,
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(horizontal = 16.dp),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    IconButton(onClick = { onDetailsClicked(recipe.toRecipe()) })
                                    {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = stringResource(R.string.arrow_forward)
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 12.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    FavoritesIcon(
                                        isFavorite = isFavorite,
                                        onStarClicked = { savedViewModel.toggleFavorite(recipe.toRecipe()) }
                                    )

                                    Spacer(modifier = Modifier.width(60.dp))

                                    MealPlanIcon(
                                        isInMealPlan = isInMealPlan,
                                        onMealPlanClicked = { savedViewModel.toggleMealPlan(recipe.toRecipe()) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealPlanEmptyScreen() {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.you_have_no_recipes_in_your_meal_plan_yet))
        }
    }
}