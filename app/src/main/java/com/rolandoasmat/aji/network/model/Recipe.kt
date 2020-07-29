package com.rolandoasmat.aji.network.model

data class Recipe(
    val id: String,
    val plate: com.rolandoasmat.aji.network.model.Plate,
    val description: String,
    val servingSizes: List<ServingSize>,
    val equipment: List<com.rolandoasmat.aji.network.model.Equipment>,
    val steps: List<Step>)