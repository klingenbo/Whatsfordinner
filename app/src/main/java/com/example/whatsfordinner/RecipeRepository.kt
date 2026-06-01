package com.example.whatsfordinner

class RecipeRepository {

    suspend fun getRecipes(): List<Recipe> {
        return RetrofitInstance.api.getRecipes()
            .meals
            .map { it.toRecipe() }
    }
}