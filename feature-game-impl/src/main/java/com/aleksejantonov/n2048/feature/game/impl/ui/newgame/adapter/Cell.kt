package com.aleksejantonov.n2048.feature.game.impl.ui.newgame.adapter

import com.aleksejantonov.n2048.core.ui.base.DiffCalculable

data class Cell(
    val id: Int,
    val value: Int? = null
) : DiffCalculable {

    override fun isTheSame(other: DiffCalculable): Boolean =
        other is Cell && this.id == other.id

    override fun isContentTheSame(other: DiffCalculable): Boolean =
        this == other
}