package com.aleksejantonov.n2048.feature.game.impl.ui

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.aleksejantonov.n2048.feature.game.impl.ui.adapter.CellsAdapter

class CellsSwipeController(
    private val adapter: CellsAdapter
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, UP or DOWN or LEFT or RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        when (direction) {
            UP    -> {

            }
            DOWN  -> {

            }
            LEFT  -> {

            }
            RIGHT -> {

            }
        }
    }
}