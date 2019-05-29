package com.aleksejantonov.n2048.feature.chooseplayer.impl.di

import com.aleksejantonov.n2048.feature.chooseplayer.api.data.ChoosePlayerStarter
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.ChoosePlayerStarterImpl
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository.IPlayerRepository
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository.PlayerRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ChoosePlayerFeatureModule {

    @Binds
    @ChoosePlayerFeatureScope
    abstract fun bindChoosePlayerStarter(choosePlayerStarter: ChoosePlayerStarterImpl): ChoosePlayerStarter

    @Binds
    @ChoosePlayerFeatureScope
    abstract fun bindPlayerRepository(playerRepository: PlayerRepository): IPlayerRepository
}