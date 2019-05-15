package com.aleksejantonov.n2048.feature.game.impl.di

import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import com.aleksejantonov.n2048.feature.game.api.di.GameFeatureApi
import com.aleksejantonov.n2048.feature.game.impl.NewGameFragment
import dagger.Component

@Component(
    modules = [GameFeatureModule::class],
    dependencies = [GameFeatureDependencies::class]
)
@FeatureScope
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
    }
}

@Component(dependencies = [CoreDatabaseApi::class])
@FeatureScope
interface GameFeatureDependencies