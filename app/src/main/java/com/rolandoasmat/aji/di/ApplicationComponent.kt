package com.rolandoasmat.aji.di

import com.rolandoasmat.aji.ingredients.IngredientsFragment
import com.rolandoasmat.aji.recipe_details.RecipeDetailsFragment
import com.rolandoasmat.aji.recipes.RecipesFragment
import com.rolandoasmat.aji.saved.SavedFragment
import com.rolandoasmat.aji.steps.StepsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ContextModule::class])
interface ApplicationComponent {
    fun inject(fragment: RecipesFragment)
    fun inject(fragment: SavedFragment)
    fun inject(fragment: RecipeDetailsFragment)
    fun inject(fragment: IngredientsFragment)
    fun inject(fragment: StepsFragment)
}