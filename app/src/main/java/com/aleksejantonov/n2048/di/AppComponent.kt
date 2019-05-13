package com.aleksejantonov.n2048.di

import com.aleksejantonov.n2048.db.impl.di.DatabaseComponentDependencies
import com.aleksejantonov.n2048.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : DatabaseComponentDependencies {
    fun inject(activity: MainActivity)
}