package com.aleksejantonov.core.navigation.impl.di

import androidx.navigation.NavController
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.impl.data.RouterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule(private val navigationController: NavController) {

    @Singleton
    @Provides
    fun provideRouter(): AppRouter = RouterImpl(navigationController)
}