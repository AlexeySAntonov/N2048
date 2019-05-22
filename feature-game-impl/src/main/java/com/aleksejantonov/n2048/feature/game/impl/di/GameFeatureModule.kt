package com.aleksejantonov.n2048.feature.game.impl.di

import com.aleksejantonov.n2048.feature.game.api.data.GameStarter
import com.aleksejantonov.n2048.feature.game.impl.data.GameStarterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GameFeatureModule {

    @Binds
    @GameFeatureScope
    abstract fun bindGameStarter(gameStarter: GameStarterImpl): GameStarter
}