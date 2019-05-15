package com.aleksejantonov.n2048.di.module

import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule(private val navigationController: NavController) {

    @Singleton
    @Provides
    fun provideNavigationController(): NavController = navigationController
}