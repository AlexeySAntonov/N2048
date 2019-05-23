package com.aleksejantonov.n2048.feature.chooseplayer.impl.di

import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.di.CoreNavigationApi
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import com.aleksejantonov.n2048.feature.chooseplayer.api.di.ChoosePlayerFeatureApi
import com.aleksejantonov.n2048.feature.chooseplayer.impl.ChoosePlayerFragment
import dagger.Component

@Component(
    modules = [ChoosePlayerFeatureModule::class],
    dependencies = [ChoosePlayerFeatureDependencies::class]
)
@ChoosePlayerFeatureScope
interface ChoosePlayerFeatureComponent : ChoosePlayerFeatureApi {
    fun inject(fragment: ChoosePlayerFragment)

    companion object {
        private var choosePlayerFeatureComponent: ChoosePlayerFeatureComponent? = null

        fun initAndGet(dependencies: ChoosePlayerFeatureDependencies): ChoosePlayerFeatureApi {
            if (choosePlayerFeatureComponent == null) {
                choosePlayerFeatureComponent = DaggerChoosePlayerFeatureComponent.builder()
                    .choosePlayerFeatureDependencies(dependencies)
                    .build()
            }
            return requireNotNull(choosePlayerFeatureComponent)
        }

        fun get(): ChoosePlayerFeatureComponent = requireNotNull(choosePlayerFeatureComponent)
    }
}

interface ChoosePlayerFeatureDependencies {
    fun databaseClientApi(): DatabaseClientApi
    fun appRouter(): AppRouter
}

@Component(dependencies = [CoreDatabaseApi::class, CoreNavigationApi::class])
@ChoosePlayerFeatureScope
interface ChoosePlayerFeatureDependenciesComponent : ChoosePlayerFeatureDependencies