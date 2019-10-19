package com.rolandoasmat.ajinetworksdk

data class RecipesResponse(val entries: List<Entry>) {
    data class Entry(val title: String,
                     val description: String)
}