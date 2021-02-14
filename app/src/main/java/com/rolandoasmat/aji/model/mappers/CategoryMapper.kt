package com.rolandoasmat.aji.model.mappers

import com.rolandoasmat.aji.model.Category

object CategoryMapper {

    fun categoryFromString(str: String): Category {
        return when(str) {
            "appetizer" -> Category.APPETIZER
            "breakfast" -> Category.BREAKFAST
            "dessert" -> Category.DESSERT
            "drinks" -> Category.DRINKS
            "entrees" -> Category.ENTREES
            else -> Category.UNKNOWN
        }
    }
}