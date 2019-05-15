package com.aleksejantonov.n2048.di

import android.content.Context
import androidx.navigation.NavController
import com.aleksejantonov.n2048.db.impl.di.DatabaseComponent
import com.aleksejantonov.n2048.di.module.AppModule
import com.aleksejantonov.n2048.di.module.NavigationModule
import com.aleksejantonov.n2048.feature.game.api.di.GameFeatureApi
import com.aleksejantonov.n2048.feature.game.impl.di.DaggerGameFeatureDependenciesComponent
import com.aleksejantonov.n2048.feature.game.impl.di.GameFeatureComponent

class ComponentManager(private val context: Context) {

    private var appComponent: AppComponent? = null

    fun appComponent(navigationController: NavController): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .navigationModule(NavigationModule(navigationController))
                .build()
        }
        return requireNotNull(appComponent)
    }

    /** FEATURE REGION */

    fun getGameFeature(): GameFeatureApi {
        return GameFeatureComponent.initAndGet(
            DaggerGameFeatureDependenciesComponent.builder()
                .coreDatabaseApi(DatabaseComponent.initAndGet(context))
                .build()
        )
    }

    /** FEATURE REGION END */
}