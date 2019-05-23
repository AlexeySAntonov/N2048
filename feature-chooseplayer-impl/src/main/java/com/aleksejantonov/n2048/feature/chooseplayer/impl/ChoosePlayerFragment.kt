package com.aleksejantonov.n2048.feature.chooseplayer.impl

import android.os.Bundle
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.ChoosePlayerFeatureComponent

class ChoosePlayerFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_choose_player

    override fun onCreate(savedInstanceState: Bundle?) {
        ChoosePlayerFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }
}