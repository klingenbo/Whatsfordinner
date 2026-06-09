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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.whatsfordinner.Recipe
import com.example.whatsfordinner.RecipesListViewModel
import com.example.whatsfordinner.RecipesUiState
import com.example.whatsfordinner.components.FavoritesIcon
import com.example.whatsfordinner.components.MealPlanIcon
import com.example.whatsfordinner.components.TopBar
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun MealPlanListScreen(
    viewModel: RecipesListViewModel,
    onDetailsClicked: (Recipe) -> Unit,
    onStarClicked: () -> Unit,
    onMealPlanClicked: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    var isFavorite by remember { mutableStateOf(false) }
    var isInMealPlan by remember { mutableStateOf(false) }

    when (uiState) {
        RecipesUiState.Loading -> MealPlanListProgressIndicator()
        RecipesUiState.Error -> RecipeListError(
            onRetryClicked = {
                viewModel.fetchRecipes()
            }
        )

        is RecipesUiState.Success -> {

            val recipes = (uiState as RecipesUiState.Success).recipes

            MealPlanListContent(
                recipes = recipes,
                onDetailsClicked = onDetailsClicked
            )
        }
    }
}

@Composable
fun MealPlanListProgressIndicator() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = ProgressIndicatorDefaults.circularColor
        )
    }
}

@Composable
fun MealPlanListContent(
    recipes: List<Recipe>,
    onDetailsClicked: (Recipe) -> Unit
) {

    Scaffold(
        topBar = {
            TopBar()
        }
    ) { innerPadding ->

        var isFavorite by remember { mutableStateOf(false) }
        var isInMealPlan by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Sage50)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Meal Plan",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recipes) { recipe ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Sage50)
                            .padding(20.dp, 20.dp),
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
                                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                                    .width(40.dp)
                                    .height(40.dp),
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

                                    IconButton(onClick = { onDetailsClicked(recipe) })
                                    {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = "Arrow forward"
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
                                        onStarClicked = { isFavorite = !isFavorite }
                                    )

                                    Spacer(modifier = Modifier.width(60.dp))

                                    MealPlanIcon(
                                        isInMealPlan = isInMealPlan,
                                        onMealPlanClicked = { isInMealPlan = !isInMealPlan }
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