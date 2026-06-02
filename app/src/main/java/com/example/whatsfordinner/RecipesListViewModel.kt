package com.example.whatsfordinner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsfordinner.api.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Recipe(
    val name: String,
    val details: String,
    val image: String
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

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe = _selectedRecipe

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes()
                _uiState.value = RecipesUiState.Success(recipes)
            } catch (e: Exception) {
                _uiState.value = RecipesUiState.Error
            }
        }
    }

    fun setSelectedRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }
}