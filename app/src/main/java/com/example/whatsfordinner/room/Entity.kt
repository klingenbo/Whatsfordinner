package com.example.whatsfordinner.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_recipes")
data class SavedRecipe(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val details: String,
    val isFavorite: Boolean = false,
    val isInMealPlan: Boolean = false
)