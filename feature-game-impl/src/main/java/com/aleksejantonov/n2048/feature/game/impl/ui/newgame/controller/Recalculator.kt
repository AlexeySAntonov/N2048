package com.aleksejantonov.n2048.feature.game.impl.ui.newgame.controller

import androidx.recyclerview.widget.ItemTouchHelper
import com.aleksejantonov.n2048.feature.game.impl.ui.newgame.adapter.Cell

class Recalculator {

    fun recalculateList(direction: Int, list: List<Cell>): List<Cell> {
        val resultList = mutableListOf<Cell>().apply { addAll(list) }
        val size: Int = Math.sqrt(list.size.toDouble()).toInt()
        when (direction) {
            ItemTouchHelper.UP    -> {
                for (i in 0 until size) {
                    val cells = arrayOf(resultList[i], resultList[i + size], resultList[i + size * 2], resultList[i + size * 3])
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                    when {
                        allNullsEquality(*cells)     -> {
                            // noop
                        }
                        doublePairEquality(*cells)   -> {
                            resultList[i + size * 3] = list[i + size * 3].copy(value = null)
                            resultList[i + size * 2] = list[i + size * 2].copy(value = null)
                            resultList[i + size] = list[i + size].copy(value = list[i + size * 3].value!! * 2)
                            resultList[i] = list[i].copy(value = list[i].value!! * 2)
                        }
                        pairEquality(*cells) != null -> {
                            val indices = pairEquality(*cells)
                            resultList[indices!!.first] = resultList[indices.first].copy(value = resultList[indices.first].value!! * 2)
                            resultList[indices.second] = resultList[indices.second].copy(value = null)
                        }
                    }
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                }
            }
            ItemTouchHelper.DOWN  -> {
                for (i in 0 until size) {
                    val cells = arrayOf(resultList[i], resultList[i + size], resultList[i + size * 2], resultList[i + size * 3])
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                    when {
                        allNullsEquality(*cells)     -> {
                            // noop
                        }
                        doublePairEquality(*cells)   -> {
                            resultList[i + size * 3] = list[i + size * 3].copy(value = list[i + size * 3].value!! * 2)
                            resultList[i + size * 2] = list[i + size * 2].copy(value = list[i].value!! * 2)
                            resultList[i + size] = list[i + size].copy(value = null)
                            resultList[i] = list[i].copy(value = null)
                        }
                        pairEquality(*cells) != null -> {
                            val indices = pairEquality(*cells)
                            resultList[indices!!.second] = resultList[indices.second].copy(value = resultList[indices.second].value!! * 2)
                            resultList[indices.first] = resultList[indices.first].copy(value = null)
                        }
                    }
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                }
            }
            ItemTouchHelper.LEFT  -> {
                for (i in 0 until size) {
                    val cells = arrayOf(resultList[i * size], resultList[i * size + 1], resultList[i * size + 2], resultList[i * size + 3])
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                    when {
                        allNullsEquality(*cells)     -> {
                            // noop
                        }
                        doublePairEquality(*cells)   -> {
                            resultList[i * size + 3] = list[i * size + 3].copy(value = null)
                            resultList[i * size + 2] = list[i * size + 2].copy(value = null)
                            resultList[i * size + 1] = list[i * size + 1].copy(value = list[i * size + 3].value!! * 2)
                            resultList[i * size] = list[i * size].copy(value = list[i * size].value!! * 2)
                        }
                        pairEquality(*cells) != null -> {
                            val indices = pairEquality(*cells)
                            resultList[indices!!.first] = resultList[indices.first].copy(value = resultList[indices.first].value!! * 2)
                            resultList[indices.second] = resultList[indices.second].copy(value = null)
                        }
                    }
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                }
            }
            ItemTouchHelper.RIGHT -> {
                for (i in 0 until size) {
                    val cells = arrayOf(resultList[i * size], resultList[i * size + 1], resultList[i * size + 2], resultList[i * size + 3])
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                    when {
                        allNullsEquality(*cells)     -> {
                            // noop
                        }
                        doublePairEquality(*cells)   -> {
                            resultList[i * size + 3] = list[i * size + 3].copy(value = list[i * size + 3].value!! * 2)
                            resultList[i * size + 2] = list[i * size + 2].copy(value = list[i * size].value!! * 2)
                            resultList[i * size + 1] = list[i * size + 1].copy(value = null)
                            resultList[i * size] = list[i * size].copy(value = null)
                        }
                        pairEquality(*cells) != null -> {
                            val indices = pairEquality(*cells)
                            resultList[indices!!.second] = resultList[indices.second].copy(value = resultList[indices.second].value!! * 2)
                            resultList[indices.first] = resultList[indices.first].copy(value = null)
                        }
                    }
                    shiftNullsIfPossible(cells = *cells, initialList = resultList, direction = direction)
                }
            }
        }
        return resultList
    }

    private fun allNullsEquality(vararg cells: Cell): Boolean {
        var allNulls = true
        for (cell in cells) if (cell.value != null) allNulls = false
        return allNulls
    }

    private fun doublePairEquality(vararg cells: Cell): Boolean {
        return cells[0].value != null && cells[0].value == cells[1].value
                && cells[2].value != null && cells[2].value == cells[3].value
    }

    private fun pairEquality(vararg cells: Cell): Pair<Int, Int>? {
        if (cells[0].value != null && cells[0].value == cells[1].value) return Pair(cells[0].id, cells[1].id)
        if (cells[1].value != null && cells[1].value == cells[2].value) return Pair(cells[1].id, cells[2].id)
        if (cells[2].value != null && cells[2].value == cells[3].value) return Pair(cells[2].id, cells[3].id)
        return null
    }

    private fun shiftNullsIfPossible(vararg cells: Cell, initialList: MutableList<Cell>, direction: Int) {
        when (direction) {
            ItemTouchHelper.UP    -> {
                for (i in 0 until cells.size - 1) {
                    if (initialList[cells[i].id].value == null) {
                        for (j in i + 1 until cells.size) {
                            if (cells[j].value != null) {
                                initialList[cells[i].id] = initialList[cells[i].id].copy(value = initialList[cells[j].id].value)
                                initialList[cells[j].id] = initialList[cells[j].id].copy(value = null)
                                break
                            }
                        }
                    }
                }
            }
            ItemTouchHelper.DOWN  -> {
                for (i in cells.size - 1 downTo 1) {
                    if (initialList[cells[i].id].value == null) {
                        for (j in i - 1 downTo 0) {
                            if (cells[j].value != null) {
                                initialList[cells[i].id] = initialList[cells[i].id].copy(value = initialList[cells[j].id].value)
                                initialList[cells[j].id] = initialList[cells[j].id].copy(value = null)
                                break
                            }
                        }
                    }
                }
            }
            ItemTouchHelper.LEFT  -> {
                for (i in 0 until cells.size - 1) {
                    if (initialList[cells[i].id].value == null) {
                        for (j in i + 1 until cells.size) {
                            if (cells[j].value != null) {
                                initialList[cells[i].id] = initialList[cells[i].id].copy(value = initialList[cells[j].id].value)
                                initialList[cells[j].id] = initialList[cells[j].id].copy(value = null)
                                break
                            }
                        }
                    }
                }
            }
            ItemTouchHelper.RIGHT -> {
                for (i in cells.size - 1 downTo 1) {
                    if (initialList[cells[i].id].value == null) {
                        for (j in i - 1 downTo 0) {
                            if (cells[j].value != null) {
                                initialList[cells[i].id] = initialList[cells[i].id].copy(value = initialList[cells[j].id].value)
                                initialList[cells[j].id] = initialList[cells[j].id].copy(value = null)
                                break
                            }
                        }
                    }
                }
            }
        }
    }
}