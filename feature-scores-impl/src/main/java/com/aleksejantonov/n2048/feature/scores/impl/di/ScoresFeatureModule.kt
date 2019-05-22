package com.aleksejantonov.n2048.feature.scores.impl.di

import com.aleksejantonov.n2048.feature.scores.api.data.ScoresStarter
import com.aleksejantonov.n2048.feature.scores.impl.data.ScoresStarterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ScoresFeatureModule {

    @Binds
    @ScoresFeatureScope
    abstract fun bindScoresStarter(scoresStarter: ScoresStarterImpl): ScoresStarter
}