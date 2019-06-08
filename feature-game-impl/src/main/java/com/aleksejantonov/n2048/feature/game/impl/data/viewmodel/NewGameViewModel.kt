package com.aleksejantonov.n2048.feature.game.impl.data.viewmodel

import androidx.lifecycle.ViewModel
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.n2048.feature.game.impl.data.repository.INewGameRepository
import com.aleksejantonov.n2048.feature.game.impl.ui.adapter.Cell
import javax.inject.Inject
import kotlin.random.Random

class NewGameViewModel @Inject constructor(
    private val newGameRepository: INewGameRepository,
    private val appRouter: AppRouter
) : ViewModel() {

    fun getInitialData(): List<Cell> {
        return initializedList().apply {
            val cellOne = randomizeCell()
            val cellTwo = randomizeCell()
            set(cellOne.id, cellOne)
            if (cellOne.id != cellTwo.id) set(cellTwo.id, cellTwo)
            else {
                if (cellOne.id + 1 < this.size) {
                    set(cellOne.id + 1, cellTwo.copy(id = cellOne.id + 1))
                } else {
                    set(cellOne.id - 1, cellTwo.copy(id = cellOne.id - 1))
                }
            }
        }
    }

    fun onBackPressed() = appRouter.navigateUp()

    private fun initializedList(): MutableList<Cell> {
        val list = mutableListOf<Cell>()
        for (i in 0 until 16) {
            list.add(Cell(i))
        }
        return list
    }

    private fun randomizeCell(): Cell {
        val id = Random.nextInt(0, 15)
        return Cell(id, 2)
    }
}