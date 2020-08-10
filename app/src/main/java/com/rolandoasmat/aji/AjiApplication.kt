package com.rolandoasmat.aji

import android.app.Application
import com.rolandoasmat.aji.di.ApplicationComponent
import com.rolandoasmat.aji.di.ContextModule
import com.rolandoasmat.aji.di.DaggerApplicationComponent

class AjiApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

    fun component(): ApplicationComponent {
        return applicationComponent
    }

}