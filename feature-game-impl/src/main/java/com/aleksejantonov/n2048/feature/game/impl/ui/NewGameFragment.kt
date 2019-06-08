package com.aleksejantonov.n2048.feature.game.impl.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.game.impl.R
import com.aleksejantonov.n2048.feature.game.impl.data.viewmodel.NewGameViewModel
import com.aleksejantonov.n2048.feature.game.impl.di.GameFeatureComponent
import javax.inject.Inject

class NewGameFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_new_game

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newGameViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[NewGameViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GameFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }
}