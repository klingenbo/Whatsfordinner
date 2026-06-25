package com.example.whatsfordinner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsfordinner.room.AppDatabase
import com.example.whatsfordinner.room.SavedRecipe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

fun SavedRecipe.toRecipe() = Recipe(
    id = id,
    name = name,
    image = image,
    details = details
)

class SavedRecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).savedRecipeDao()

    val favorites: StateFlow<List<SavedRecipe>> = dao.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val mealPlan: StateFlow<List<SavedRecipe>> = dao.getMealPlan()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            val existing = dao.getById(recipe.id)
            if (existing == null) {
                dao.upsert(
                    SavedRecipe(
                        id = recipe.id,
                        name = recipe.name,
                        image = recipe.image,
                        details = recipe.details,
                        isFavorite = true
                    )
                )
            } else {
                dao.upsert(existing.copy(isFavorite = !existing.isFavorite))
            }
        }
    }

    fun toggleMealPlan(recipe: Recipe) {
        viewModelScope.launch {
            val existing = dao.getById(recipe.id)
            if (existing == null) {
                dao.upsert(
                    SavedRecipe(
                        id = recipe.id,
                        name = recipe.name,
                        image = recipe.image,
                        details = recipe.details,
                        isInMealPlan = true
                    )
                )
            } else {
                dao.upsert(existing.copy(isInMealPlan = !existing.isInMealPlan))
            }
        }
    }

    fun getRandomFavorite(): SavedRecipe? {
        return favorites.value.randomOrNull()
    }

    fun getRandomMealPlan(): SavedRecipe? {
        return mealPlan.value.randomOrNull()
    }
}