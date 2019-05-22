package com.aleksejantonov.core.navigation.api.di

import com.aleksejantonov.core.navigation.api.data.AppRouter

interface CoreNavigationApi {
    fun appRouter(): AppRouter
}