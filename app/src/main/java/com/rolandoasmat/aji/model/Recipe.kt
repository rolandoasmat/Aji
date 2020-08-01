package com.rolandoasmat.aji.network.model

data class Recipe(
    val id: String,
    val plate: Plate,
    val description: String,
    val servingSizes: List<ServingSize>,
    val equipment: List<Equipment>,
    val steps: List<Step>)