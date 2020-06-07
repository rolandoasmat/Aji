package com.rolandoasmat.ajinetworksdk.model

data class RecipeDetails(
    val id: String,
    val title: String,
    val description: String,
    val imageURL: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>,
    val steps: List<Step>)