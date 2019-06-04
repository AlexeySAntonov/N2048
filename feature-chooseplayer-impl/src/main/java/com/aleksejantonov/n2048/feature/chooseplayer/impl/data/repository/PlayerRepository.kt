package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.model.Player
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val databaseClientApi: DatabaseClientApi
) : IPlayerRepository {

    override fun createPlayer(player: Player) =
        databaseClientApi.playerDao().insertPlayer(player)

    override fun deletePlayer(id: Long) =
        databaseClientApi.playerDao().removePlayer(id)

    override fun observeAllPlayers(): LiveData<List<Player>> =
        databaseClientApi.playerDao().getAllPlayers()

    override fun observeSelectedPlayer(): LiveData<List<Player>> =
        databaseClientApi.playerDao().observeSelectedPlayer()

    override fun selectPlayer(id: Long) {
        val currentId = databaseClientApi.playerDao().getSelectedPlayer()?.id
        if (currentId == id) return
        currentId?.let {
            databaseClientApi.playerDao().selectPlayer(it, false)
        }
        databaseClientApi.playerDao().selectPlayer(id, true)
    }
}