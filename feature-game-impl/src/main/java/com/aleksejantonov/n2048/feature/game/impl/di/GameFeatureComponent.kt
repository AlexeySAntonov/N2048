package com.aleksejantonov.n2048.feature.game.impl.di

import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.di.CoreNavigationApi
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import com.aleksejantonov.n2048.feature.game.api.di.GameFeatureApi
import com.aleksejantonov.n2048.feature.game.impl.di.annotations.GameFeatureScope
import com.aleksejantonov.n2048.feature.game.impl.di.module.GameFeatureModule
import com.aleksejantonov.n2048.feature.game.impl.di.module.GameFeatureViewModelModule
import com.aleksejantonov.n2048.feature.game.impl.ui.NewGameFragment
import dagger.Component

@Component(
    modules = [GameFeatureModule::class, GameFeatureViewModelModule::class],
    dependencies = [GameFeatureDependencies::class]
)
@GameFeatureScope
interface GameFeatureComponent : GameFeatureApi {
    fun inject(fragment: NewGameFragment)

    companion object {
        private var gameFeatureComponent: GameFeatureComponent? = null

        fun initAndGet(dependencies: GameFeatureDependencies): GameFeatureApi {
            if (gameFeatureComponent == null) {
                gameFeatureComponent = DaggerGameFeatureComponent.builder()
                    .gameFeatureDependencies(dependencies)
                    .build()
            }
            return requireNotNull(gameFeatureComponent)
        }

        fun get(): GameFeatureComponent = requireNotNull(gameFeatureComponent)

        fun release() {
            gameFeatureComponent = null
        }
    }
}

interface GameFeatureDependencies {
    fun databaseClientApi(): DatabaseClientApi
    fun appRouter(): AppRouter
}

@Component(dependencies = [CoreDatabaseApi::class, CoreNavigationApi::class])
@GameFeatureScope
interface GameFeatureDependenciesComponent : GameFeatureDependencies