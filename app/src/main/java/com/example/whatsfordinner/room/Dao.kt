package com.example.whatsfordinner.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedRecipeDao {

    @Query("SELECT * FROM saved_recipes WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<SavedRecipe>>

    @Query("SELECT * FROM saved_recipes WHERE isInMealPlan = 1")
    fun getMealPlan(): Flow<List<SavedRecipe>>

    @Upsert
    suspend fun upsert(recipe: SavedRecipe)

    @Delete
    suspend fun delete(recipe: SavedRecipe)

    @Query("SELECT * FROM saved_recipes WHERE id = :id")
    suspend fun getById(id: String): SavedRecipe?
}