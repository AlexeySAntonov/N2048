package com.aleksejantonov.n2048.di

import android.content.Context
import androidx.navigation.NavController
import com.aleksejantonov.core.navigation.api.di.CoreNavigationApi
import com.aleksejantonov.core.navigation.impl.di.NavigationComponent
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import com.aleksejantonov.n2048.db.impl.di.DatabaseComponent
import com.aleksejantonov.n2048.di.module.AppModule
import com.aleksejantonov.n2048.feature.chooseplayer.api.di.ChoosePlayerFeatureApi
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.ChoosePlayerFeatureComponent
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.DaggerChoosePlayerFeatureDependenciesComponent
import com.aleksejantonov.n2048.feature.game.api.di.GameFeatureApi
import com.aleksejantonov.n2048.feature.game.impl.di.DaggerGameFeatureDependenciesComponent
import com.aleksejantonov.n2048.feature.game.impl.di.GameFeatureComponent
import com.aleksejantonov.n2048.feature.scores.api.di.ScoreFeatureApi
import com.aleksejantonov.n2048.feature.scores.impl.di.DaggerScoresFeatureDependenciesComponent
import com.aleksejantonov.n2048.feature.scores.impl.di.ScoresFeatureComponent

class ComponentManager(private val context: Context) {

    private var appComponent: AppComponent? = null
    private lateinit var databaseApi: CoreDatabaseApi
    private lateinit var navigationApi: CoreNavigationApi

    fun appComponent(navigationController: NavController): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()

            initDatabase()
            initNavigation(navigationController)
        }
        return requireNotNull(appComponent)
    }

    /** GLOBAL APIs initialization */

    private fun initDatabase() {
        databaseApi = DatabaseComponent.initAndGet(context)
    }

    private fun initNavigation(navigationController: NavController) {
        navigationApi = NavigationComponent.initAndGet(navigationController)
    }

    /** GLOBAL APIs initialization END */

    /** FEATURE REGION */

    fun getGameFeature(): GameFeatureApi {
        return GameFeatureComponent.initAndGet(
            DaggerGameFeatureDependenciesComponent.builder()
                .coreDatabaseApi(databaseApi)
                .coreNavigationApi(navigationApi)
                .build()
        )
    }

    fun getScoresFeature(): ScoreFeatureApi {
        return ScoresFeatureComponent.initAndGet(
            DaggerScoresFeatureDependenciesComponent.builder()
                .coreDatabaseApi(databaseApi)
                .coreNavigationApi(navigationApi)
                .build()
        )
    }

    fun getChoosePlayerFeature(): ChoosePlayerFeatureApi {
        return ChoosePlayerFeatureComponent.initAndGet(
            DaggerChoosePlayerFeatureDependenciesComponent.builder()
                .coreDatabaseApi(databaseApi)
                .coreNavigationApi(navigationApi)
                .build()
        )
    }

    /** FEATURE REGION END */
}