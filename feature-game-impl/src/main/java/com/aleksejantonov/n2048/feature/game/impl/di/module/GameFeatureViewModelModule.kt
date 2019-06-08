package com.aleksejantonov.n2048.feature.game.impl.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleksejantonov.n2048.feature.game.impl.data.viewmodel.NewGameViewModel
import com.aleksejantonov.n2048.feature.game.impl.data.viewmodel.GameFeatureViewModelFactory
import com.aleksejantonov.n2048.feature.game.impl.di.annotations.GameFeatureScope
import com.aleksejantonov.n2048.feature.game.impl.di.annotations.GameFeatureViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GameFeatureViewModelModule {

    @Binds
    @GameFeatureScope
    internal abstract fun bindViewModelFactory(factory: GameFeatureViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @GameFeatureScope
    @GameFeatureViewModelKey(NewGameViewModel::class)
    internal abstract fun newGameViewModel(viewModel: NewGameViewModel): ViewModel
}