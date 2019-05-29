package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.model.Player

interface IPlayerRepository {

    fun getAllPlayers(): LiveData<List<Player>>
}