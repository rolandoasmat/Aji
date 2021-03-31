package com.rolandoasmat.aji.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteRecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): RecipesDAO
}