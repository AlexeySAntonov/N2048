package com.aleksejantonov.n2048.feature.game.impl.ui.newgame.controller

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.aleksejantonov.n2048.feature.game.impl.data.viewmodel.NewGameViewModel

class CellsTouchListener(
    private val viewModel: NewGameViewModel,
    private val recalculator: Recalculator
) : View.OnTouchListener {

    private var flingOut: Boolean = false
    private var oldX: Float = 0f
    private var oldY: Float = 0f

    override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {
        return when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                oldX = motionEvent.x
                oldY = motionEvent.y
                flingOut = false
                true
            }
            MotionEvent.ACTION_UP   -> {
                flingOut = false
                true
            }
            MotionEvent.ACTION_MOVE -> {
                if (flingOut.not()) {
                    val diffY = motionEvent.y - oldY
                    val diffX = motionEvent.x - oldX
                    if (Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX) > THRESHOLD) {
                        if (diffX > 0) {
                            val currentCells = viewModel.getCellsState().value
                            viewModel.setCellsState(recalculator.recalculateList(ItemTouchHelper.RIGHT, currentCells!!))
                            flingOut = true
                        } else {
                            val currentCells = viewModel.getCellsState().value
                            viewModel.setCellsState(recalculator.recalculateList(ItemTouchHelper.LEFT, currentCells!!))
                            flingOut = true
                        }
                    } else if (Math.abs(diffY) > Math.abs(diffX) && Math.abs(diffY) > THRESHOLD) {
                        if (diffY > 0) {
                            val currentCells = viewModel.getCellsState().value
                            viewModel.setCellsState(recalculator.recalculateList(ItemTouchHelper.DOWN, currentCells!!))
                            flingOut = true
                        } else {
                            val currentCells = viewModel.getCellsState().value
                            viewModel.setCellsState(recalculator.recalculateList(ItemTouchHelper.UP, currentCells!!))
                            flingOut = true
                        }
                    }
                }
                true
            }
            else                    -> false
        }
    }

    companion object {
        private const val THRESHOLD = 50 // (px)
    }
}