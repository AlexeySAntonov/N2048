package com.aleksejantonov.n2048.core.ui.base

interface DiffCalculable {

    fun isTheSame(other: DiffCalculable): Boolean

    fun isContentTheSame(other: DiffCalculable): Boolean

}