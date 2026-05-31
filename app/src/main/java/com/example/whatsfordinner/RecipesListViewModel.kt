package com.example.whatsfordinner

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Recipe(
    val name: String,
    val details: String,
    val image: String
)

class RecipesListViewModel : ViewModel() {

    private val _recipeList = MutableStateFlow(dummyData())
    val recipeList: StateFlow<List<Recipe>> = _recipeList

    fun dummyData(): List<Recipe> {
        val recipe = listOf(
            Recipe(name = "Spagetti", details = "Cook for 8-10 min", image = "SpagettiImg"),
            Recipe(name = "Bacon", details = "Fry in a pan", image = "BaconImg"),
            Recipe(name = "Stew", details = "Cook all ingrediens in a pot", image = "StewImg"),
            Recipe(name = "Sausage", details = "Fry in a pan", image = "SausageImg"),
            Recipe(
                name = "Pancakes",
                details = "Make the batter and fry in a pan",
                image = "PancakeImg"
            )
        )
        return recipe
    }
}