package com.aleksejantonov.n2048.feature.scores.impl.di

import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.di.CoreNavigationApi
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import com.aleksejantonov.n2048.feature.scores.api.di.ScoreFeatureApi
import com.aleksejantonov.n2048.feature.scores.impl.ScoresFragment
import dagger.Component

@Component(
    modules = [ScoresFeatureModule::class],
    dependencies = [ScoresFeatureDependencies::class]
)
@ScoresFeatureScope
interface ScoresFeatureComponent : ScoreFeatureApi {
    fun inject(fragment: ScoresFragment)

    companion object {
        private var scoresFeatureComponent: ScoresFeatureComponent? = null

        fun initAndGet(dependencies: ScoresFeatureDependencies): ScoreFeatureApi {
            if (scoresFeatureComponent == null) {
                scoresFeatureComponent = DaggerScoresFeatureComponent.builder()
                    .scoresFeatureDependencies(dependencies)
                    .build()
            }
            return requireNotNull(scoresFeatureComponent)
        }

        fun get(): ScoresFeatureComponent = requireNotNull(scoresFeatureComponent)
    }
}

interface ScoresFeatureDependencies {
    fun databaseClientApi(): DatabaseClientApi
    fun appRouter(): AppRouter
}

@Component(dependencies = [CoreDatabaseApi::class, CoreNavigationApi::class])
@ScoresFeatureScope
interface ScoresFeatureDependenciesComponent : ScoresFeatureDependencies