package com.aleksejantonov.n2048.feature.game.impl.ui.newgame

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.game.impl.R
import com.aleksejantonov.n2048.feature.game.impl.data.viewmodel.NewGameViewModel
import com.aleksejantonov.n2048.feature.game.impl.di.GameFeatureComponent
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.controller.CellsSwipeController
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.adapter.CellsAdapter
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.controller.Recalculator
import kotlinx.android.synthetic.main.fragment_new_game.*
import javax.inject.Inject

class NewGameFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_new_game

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newGameViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[NewGameViewModel::class.java]
    }

    private val adapter by lazy { CellsAdapter() }

    private val cellsSwipeController by lazy {
        CellsSwipeController(newGameViewModel, Recalculator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GameFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
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
            val itemTouchHelper = ItemTouchHelper(cellsSwipeController)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun observeCellsState() {
        with(newGameViewModel) {
            getCellsState()
                .observe(
                    this@NewGameFragment,
                    Observer {
                        adapter.updateList(it)
                    }
                )
            initializedData()
        }
    }
}