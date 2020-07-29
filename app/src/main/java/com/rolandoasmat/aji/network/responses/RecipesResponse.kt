package com.rolandoasmat.aji.network.responses

data class RecipesResponse(val entries: List<Entry>) {
    data class Entry(val title: String,
                     val description: String)
}