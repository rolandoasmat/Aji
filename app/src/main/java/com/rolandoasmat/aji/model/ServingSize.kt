package com.rolandoasmat.aji.model

import com.rolandoasmat.aji.model.Ingredient

data class ServingSize(
    val title: String,
    val ingredients: List<Ingredient>)