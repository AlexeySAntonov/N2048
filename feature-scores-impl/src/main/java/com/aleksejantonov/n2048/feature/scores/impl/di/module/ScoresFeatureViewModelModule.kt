package com.aleksejantonov.n2048.feature.scores.impl.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleksejantonov.n2048.feature.scores.impl.data.viewmodel.ScoresFeatureViewModelFactory
import com.aleksejantonov.n2048.feature.scores.impl.data.viewmodel.ScoresViewModel
import com.aleksejantonov.n2048.feature.scores.impl.di.annotations.ScoresFeatureScope
import com.aleksejantonov.n2048.feature.scores.impl.di.annotations.ScoresFeatureViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ScoresFeatureViewModelModule {

    @Binds
    @ScoresFeatureScope
    internal abstract fun bindViewModelFactory(factory: ScoresFeatureViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ScoresFeatureScope
    @ScoresFeatureViewModelKey(ScoresViewModel::class)
    internal abstract fun scoresViewModel(viewModel: ScoresViewModel): ViewModel
}