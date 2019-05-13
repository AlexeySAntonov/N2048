package com.aleksejantonov.n2048.di

import android.content.Context
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import com.aleksejantonov.n2048.db.impl.di.DaggerDatabaseComponent

class ComponentManager(private val context: Context) {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    private val databaseComponent: CoreDatabaseApi by lazy {
        DaggerDatabaseComponent.builder()
            .databaseComponentDependencies(appComponent)
            .build()
    }

    fun appComponent() = appComponent
    fun databaseComponent() = databaseComponent
}