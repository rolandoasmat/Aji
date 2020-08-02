package com.rolandoasmat.aji.di

import com.rolandoasmat.aji.recipes.RecipesFragment
import com.rolandoasmat.aji.saved.SavedFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(fragment: RecipesFragment)
    fun inject(fragment: SavedFragment)
}