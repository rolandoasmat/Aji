package com.rolandoasmat.aji.di

import android.content.Context
import androidx.room.Room
import com.rolandoasmat.aji.CoroutineContextProvider
import com.rolandoasmat.aji.db.AppDatabase
import com.rolandoasmat.aji.db.DatabaseRepository
import com.rolandoasmat.aji.db.RecipesDAO
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    companion object {
        private const val DATABASE_NAME = "recipes-database"
    }

    @Provides
    fun provideDAO(context: Context): RecipesDAO {
        return Room.databaseBuilder(context,
            AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
            .dao()
    }

    @Provides
    fun provideRecipesRepository(dao: RecipesDAO, coroutineContextProvider: CoroutineContextProvider): DatabaseRepository {
        return DatabaseRepository(dao, coroutineContextProvider)
    }

}