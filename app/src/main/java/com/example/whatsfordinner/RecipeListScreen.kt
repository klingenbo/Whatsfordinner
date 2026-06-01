package com.example.whatsfordinner

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun RecipeListScreen(
    viewModel: RecipesListViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        RecipesUiState.Loading -> CircularProgressIndicator()
        RecipesUiState.Error -> RecipeListError()
        is RecipesUiState.Success -> {
            RecipeListContent(recipes = (uiState as RecipesUiState.Success).recipes)
        }
    }
}


@Composable
fun RecipeListContent(recipes: List<Recipe>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sage50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Recipes",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
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
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {

                        Image(
                            painter = painterResource(recipe.image),
                            contentDescription = "Recipe image",
                            modifier = Modifier
                                .padding(20.dp, 10.dp, 0.dp, 10.dp)
                                .width(50.dp)
                                .height(50.dp),
                            alignment = Alignment.CenterStart,
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = recipe.name,
                            textAlign = TextAlign.Right,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                    Text(
                        text = recipe.details,
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(20.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }
}