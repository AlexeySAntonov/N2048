package com.aleksejantonov.n2048.feature.scores.impl.di.module

import com.aleksejantonov.n2048.feature.scores.api.data.ScoresStarter
import com.aleksejantonov.n2048.feature.scores.impl.data.ScoresStarterImpl
import com.aleksejantonov.n2048.feature.scores.impl.data.repository.IScoresRepository
import com.aleksejantonov.n2048.feature.scores.impl.data.repository.ScoresRepository
import com.aleksejantonov.n2048.feature.scores.impl.di.annotations.ScoresFeatureScope
import dagger.Binds
import dagger.Module

@Module
abstract class ScoresFeatureModule {

    @Binds
    @ScoresFeatureScope
    abstract fun bindScoresStarter(scoresStarter: ScoresStarterImpl): ScoresStarter

    @Binds
    @ScoresFeatureScope
    abstract fun bindScoresRepository(scoresRepository: ScoresRepository): IScoresRepository
}