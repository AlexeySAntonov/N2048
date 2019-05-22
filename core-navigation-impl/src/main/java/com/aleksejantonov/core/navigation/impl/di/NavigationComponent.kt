package com.aleksejantonov.core.navigation.impl.di

import androidx.navigation.NavController
import com.aleksejantonov.core.navigation.api.di.CoreNavigationApi
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface NavigationComponent : CoreNavigationApi {

    companion object {
        private var navigationComponent: NavigationComponent? = null

        fun initAndGet(navigationController: NavController): CoreNavigationApi {
            if (navigationComponent == null) {
                navigationComponent = DaggerNavigationComponent.builder()
                    .navigationModule(NavigationModule(navigationController))
                    .build()
            }
            return requireNotNull(navigationComponent)
        }

        fun get(): CoreNavigationApi = requireNotNull(navigationComponent)
    }
}