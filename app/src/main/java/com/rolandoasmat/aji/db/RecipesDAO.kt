package com.rolandoasmat.aji.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRecipe(favoriteRecipe: FavoriteRecipeEntity)

    @Query("DELETE FROM favorite_recipes WHERE recipeID LIKE :recipeID")
    fun deleteFavoriteRecipe(recipeID: Int)

    @Query("SELECT * FROM favorite_recipes")
    fun loadFavoriteRecipes(): LiveData<List<FavoriteRecipeEntity>>

    @Query("SELECT * FROM favorite_recipes WHERE recipeID LIKE :recipeID")
    fun getFavoriteRecipe(recipeID: Int): LiveData<FavoriteRecipeEntity>

}