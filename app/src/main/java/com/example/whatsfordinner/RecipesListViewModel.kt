package com.example.whatsfordinner

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Recipe(
    val name: String,
    val details: String,
    val image: Int
)

class RecipesListViewModel : ViewModel() {

    private val _recipeList = MutableStateFlow(dummyData())
    val recipeList: StateFlow<List<Recipe>> = _recipeList

    fun dummyData(): List<Recipe> {
        val recipe = listOf(
            Recipe(
                name = "Spagetti",
                details = "Cook for 8-10 min. Cook for 8-10 min. Cook for 8-10 min. Cook for 8-10 min. Cook for 8-10 min.",
                image = R.drawable.cooking
            ),
            Recipe(
                name = "Bacon",
                details = "Fry in a pan. Fry in a pan. Fry in a pan. Fry in a pan. Fry in a pan. Fry in a pan.",
                image = R.drawable.cooking
            ),
            Recipe(
                name = "Stew",
                details = "Cook all ingredients in a pot. Cook all ingredients in a pot. Cook all ingredients in a pot.",
                image = R.drawable.cooking
            ),
            Recipe(
                name = "Sausage",
                details = "Fry in a pan. Fry in a pan. Fry in a pan. Fry in a pan. Fry in a pan. Fry in a pan.",
                image = R.drawable.cooking
            ),
            Recipe(
                name = "Pancakes",
                details = "Make the batter and fry in a pan. Make the batter and fry in a pan. Make the batter and fry in a pan. Make the batter and fry in a pan. Make the batter and fry in a pan.",
                image = R.drawable.cook_book
            )
        )
        return recipe
    }
}