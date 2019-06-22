package com.aleksejantonov.n2048.feature.scores.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.model.Player

interface IScoresRepository {
    fun observePlayers(): LiveData<List<Player>>
}