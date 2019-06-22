package com.aleksejantonov.n2048.feature.scores.impl.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.scores.impl.R
import com.aleksejantonov.n2048.feature.scores.impl.data.viewmodel.ScoresViewModel
import com.aleksejantonov.n2048.feature.scores.impl.di.ScoresFeatureComponent
import kotlinx.android.synthetic.main.fragment_scores.*
import javax.inject.Inject

class ScoresFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_scores

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val scoresViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ScoresViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ScoresFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlayers()
    }

    private fun observePlayers() {
        scoresViewModel
            .observePlayers()
            .observe(
                this,
                Observer {
                    if (it.isNotEmpty()) chartView.setPlayers(it)
                }
            )
    }
}