package com.example.whatsfordinner

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.whatsfordinner.ui.theme.Sage50

@Composable
fun RecipeDetails(
    viewModel: RecipesListViewModel
) {

    val recipe by viewModel.selectedRecipe.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Sage50),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        AsyncImage(
            model = recipe?.image ?: "N/A",
            contentDescription = recipe?.name ?: "No Titel",
            modifier = Modifier
                .height(200.dp)
                .width(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = recipe?.name ?: "",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 40.dp)
        )

        Text(
            text = recipe?.details ?: "",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(vertical = 30.dp, horizontal = 30.dp)
        )
    }
}