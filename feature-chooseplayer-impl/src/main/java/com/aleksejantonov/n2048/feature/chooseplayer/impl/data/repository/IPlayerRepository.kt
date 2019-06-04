package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.model.Player

interface IPlayerRepository {

    fun createPlayer(player: Player)
    fun deletePlayer(id: Long)
    fun observeAllPlayers(): LiveData<List<Player>>
    fun observeSelectedPlayer(): LiveData<List<Player>>
    fun selectPlayer(id: Long)
}