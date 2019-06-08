package com.aleksejantonov.n2048.feature.game.impl.di.module

import com.aleksejantonov.n2048.feature.game.api.data.GameStarter
import com.aleksejantonov.n2048.feature.game.impl.data.GameStarterImpl
import com.aleksejantonov.n2048.feature.game.impl.data.repository.INewGameRepository
import com.aleksejantonov.n2048.feature.game.impl.data.repository.NewGameRepository
import com.aleksejantonov.n2048.feature.game.impl.di.annotations.GameFeatureScope
import dagger.Binds
import dagger.Module

@Module
abstract class GameFeatureModule {

    @Binds
    @GameFeatureScope
    abstract fun bindGameStarter(gameStarter: GameStarterImpl): GameStarter

    @Binds
    @GameFeatureScope
    abstract fun bindNewGameRepository(newGameRepository: NewGameRepository): INewGameRepository
}