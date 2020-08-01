package com.rolandoasmat.aji.di

import com.rolandoasmat.aji.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(app: HomeFragment)
}