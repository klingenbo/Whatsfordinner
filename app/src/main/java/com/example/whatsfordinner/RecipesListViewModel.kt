package com.example.whatsfordinner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsfordinner.api.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class Recipe(
    val name: String,
    val details: String,
    val image: String,
    val id: String = UUID.randomUUID().toString()
)

sealed class RecipesUiState {
    object Loading : RecipesUiState()
    data class Success(val recipes: List<Recipe>) : RecipesUiState()
    object Error : RecipesUiState()
}

class RecipesListViewModel : ViewModel() {

    val repository = RecipeRepository()
    private val _uiState = MutableStateFlow<RecipesUiState>(RecipesUiState.Loading)
    val uiState: StateFlow<RecipesUiState> = _uiState

    var selectedRecipe by mutableStateOf<Recipe?>(null)
        private set

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes()
                _uiState.value = RecipesUiState.Success(recipes)
            } catch (e: Exception) {
                _uiState.value = RecipesUiState.Error
            }
        }
    }

    fun selectRecipe(recipe: Recipe) {
        selectedRecipe = recipe
    }
}