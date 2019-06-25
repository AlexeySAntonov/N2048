package com.aleksejantonov.n2048.feature.game.impl.ui.newgame

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.core.ui.base.isVisible
import com.aleksejantonov.n2048.feature.game.impl.R
import com.aleksejantonov.n2048.feature.game.impl.data.viewmodel.NewGameViewModel
import com.aleksejantonov.n2048.feature.game.impl.di.GameFeatureComponent
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.adapter.CellsAdapter
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.controller.CellsTouchListener
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.controller.Recalculator
import kotlinx.android.synthetic.main.fragment_new_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewGameFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_new_game

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newGameViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[NewGameViewModel::class.java]
    }

    private val adapter by lazy { CellsAdapter() }

    private val cellsTouchListener by lazy {
        CellsTouchListener(newGameViewModel, Recalculator())
    }

    private var oldScore: Long = Long.MAX_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        GameFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initViews()
        observeSelectedPlayer()
        observeCellsState()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (activity?.isFinishing == true) {
            GameFeatureComponent.release()
            return
        }

        if (isStatedSaved) {
            isStatedSaved = false
            return
        }

        if (isRemoving) {
            GameFeatureComponent.release()
        }
    }

    private fun initList() {
        with(recycler) {
            adapter = this@NewGameFragment.adapter
            setOnTouchListener(cellsTouchListener)
        }
    }

    private fun initViews() {
        saveAndExitButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                newGameViewModel.saveScoreAsync().await()
                newGameViewModel.onBackPressed()
            }
        }
    }

    private fun observeSelectedPlayer() {
        newGameViewModel.observeSelectedPlayer()
            .observe(
                this,
                Observer {
                    if (it.isNotEmpty()) {
                        player.text = context?.getString(R.string.player_formatter, it.first().name)
                        oldScore = it.first().score
                    } else {
                        anonImg.isVisible = true
                        player.text = context?.getString(R.string.player_anon)
                        oldScore = 0L
                    }
                }
            )
    }

    private fun observeCellsState() {
        with(newGameViewModel) {
            getCellsState()
                .observe(
                    this@NewGameFragment,
                    Observer {
                        adapter.updateList(it)
                        val maxCellValue = it.maxBy { cell -> cell.value ?: 0 }?.value
                        scores.text = context?.getString(R.string.score_formatter, maxCellValue)
                        saveGroup.isVisible = maxCellValue ?: 0 > oldScore
                    }
                )
            initializedData()
        }
    }
}