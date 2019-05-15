package com.aleksejantonov.n2048.feature.game.impl

import android.os.Bundle
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.game.impl.di.GameFeatureComponent

class NewGameFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_new_game

    override fun onCreate(savedInstanceState: Bundle?) {
        GameFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }
}