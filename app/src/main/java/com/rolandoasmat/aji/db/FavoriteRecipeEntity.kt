package com.rolandoasmat.aji.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipeEntity(
    @PrimaryKey
    val recipeID: Int,
    val name: String,
    @ColumnInfo(name = "poster_url")
    val posterURL: String
)