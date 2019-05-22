package com.aleksejantonov.n2048.di

import com.aleksejantonov.n2048.di.module.AppModule
import com.aleksejantonov.n2048.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
}