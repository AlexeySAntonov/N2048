package com.aleksejantonov.n2048.feature.chooseplayer.impl.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel.ChoosePlayerViewModel
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel.ChoosePlayerViewModelFactory
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel.NewPlayerViewModel
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.annotations.ChoosePlayerFeatureScope
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.annotations.ChoosePlayerViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChoosePlayerViewModelModule {

    @Binds
    @ChoosePlayerFeatureScope
    internal abstract fun bindViewModelFactory(
        factory: ChoosePlayerViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ChoosePlayerFeatureScope
    @ChoosePlayerViewModelKey(ChoosePlayerViewModel::class)
    internal abstract fun choosePlayerViewModel(viewModel: ChoosePlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ChoosePlayerFeatureScope
    @ChoosePlayerViewModelKey(NewPlayerViewModel::class)
    internal abstract fun newPlayerViewModel(viewModel: NewPlayerViewModel): ViewModel
}