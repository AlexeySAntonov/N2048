package com.aleksejantonov.n2048.feature.scores.impl.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.scores.impl.R
import com.aleksejantonov.n2048.feature.scores.impl.data.viewmodel.ScoresViewModel
import com.aleksejantonov.n2048.feature.scores.impl.di.ScoresFeatureComponent
import com.aleksejantonov.n2048.feature.scores.impl.ui.adapter.ScoresAdapter
import kotlinx.android.synthetic.main.fragment_scores.*
import javax.inject.Inject

class ScoresFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_scores

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val scoresViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ScoresViewModel::class.java]
    }

    private val adapter by lazy { ScoresAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        ScoresFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initList()
        observePlayers()
    }

    private fun initToolbar() {
        with(toolbar as Toolbar) {
            setTitle(R.string.scores_toolbar_title)
            setNavigationOnClickListener { scoresViewModel.onBackPressed() }
        }
    }

    private fun initList() {
        with(recyclerView) {
            adapter = this@ScoresFragment.adapter
        }
    }

    private fun observePlayers() {
        scoresViewModel
            .observePlayers()
            .observe(
                this,
                Observer {
                    if (it.isNotEmpty()) {
                        chartView.setPlayers(it)
                        adapter.updateItems(it)
                    }
                }
            )
    }
}