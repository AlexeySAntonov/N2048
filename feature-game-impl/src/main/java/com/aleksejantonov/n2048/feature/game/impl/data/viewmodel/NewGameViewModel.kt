package com.aleksejantonov.n2048.feature.game.impl.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.n2048.feature.game.impl.data.repository.INewGameRepository
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.adapter.Cell
import com.aleksejantonov.n2048.model.Player
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlin.random.Random

class NewGameViewModel @Inject constructor(
    private val newGameRepository: INewGameRepository,
    private val appRouter: AppRouter
) : ViewModel() {

    private var cellsState = MutableLiveData<List<Cell>>()

    fun getCellsState() = cellsState

    fun setCellsState(cells: List<Cell>) {
        cellsState.value = cells
    }

    fun initializedData() {
        cellsState.value = initializedList().apply {
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

    fun observeSelectedPlayer(): LiveData<List<Player>> =
        Transformations.distinctUntilChanged(newGameRepository.observeSelectedPlayer())

    fun saveScoreAsync(): Deferred<Unit?> {
        return GlobalScope.async {
            cellsState.value?.maxBy { cell -> cell.value ?: 0 }?.value?.toLong()?.let {
                newGameRepository.updateScoreAsync(it)
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