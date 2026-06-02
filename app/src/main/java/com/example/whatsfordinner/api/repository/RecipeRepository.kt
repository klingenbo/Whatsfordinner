package com.example.whatsfordinner.api.repository

import com.example.whatsfordinner.Recipe
import com.example.whatsfordinner.api.RetrofitInstance
import com.example.whatsfordinner.api.toRecipe

class RecipeRepository {

    suspend fun getRecipes(): List<Recipe> {
        return RetrofitInstance.api.getRecipes()
            .meals
            .map { it.toRecipe() }
    }
}