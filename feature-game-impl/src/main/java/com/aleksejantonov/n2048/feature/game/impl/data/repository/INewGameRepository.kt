package com.aleksejantonov.n2048.feature.game.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.model.Player

interface INewGameRepository {
    fun observeSelectedPlayer(): LiveData<List<Player>>
    fun updateScoreAsync(newScore: Long)
}