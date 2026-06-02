package com.example.whatsfordinner.api

data class RecipeResponseDto(
    val meals: List<RecipeDto>
)
data class RecipeDto(
    val strInstructions: String?,
    val strMeal: String?,
    val strMealThumb: String?,
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        name = strMeal ?: "",
        details = strInstructions ?: "",
        image = strMealThumb ?: ""
    )
}