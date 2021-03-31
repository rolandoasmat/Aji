package com.rolandoasmat.aji.di

import com.rolandoasmat.aji.info.InfoFragment
import com.rolandoasmat.aji.ingredients.IngredientsFragment
import com.rolandoasmat.aji.recipe_details.RecipeDetailsActivity
import com.rolandoasmat.aji.recipe_details.RecipeDetailsFragment
import com.rolandoasmat.aji.home.HomeFragment
import com.rolandoasmat.aji.saved.SavedFragment
import com.rolandoasmat.aji.steps.StepsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, ContextModule::class])
interface ApplicationComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: SavedFragment)
    fun inject(activity: RecipeDetailsActivity)
    fun inject(fragment: RecipeDetailsFragment)
    fun inject(fragment: IngredientsFragment)
    fun inject(fragment: StepsFragment)
    fun inject(fragment: InfoFragment)
}