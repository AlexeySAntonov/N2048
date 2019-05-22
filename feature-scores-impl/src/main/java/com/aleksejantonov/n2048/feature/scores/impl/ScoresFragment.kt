package com.aleksejantonov.n2048.feature.scores.impl

import android.os.Bundle
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.scores.impl.di.ScoresFeatureComponent

class ScoresFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_scores

    override fun onCreate(savedInstanceState: Bundle?) {
        ScoresFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }
}