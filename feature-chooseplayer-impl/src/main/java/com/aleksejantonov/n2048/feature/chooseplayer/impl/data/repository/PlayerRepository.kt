package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.model.Player
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val databaseClientApi: DatabaseClientApi
) : IPlayerRepository {

    override fun createPlayer(player: Player) =
        databaseClientApi
            .playerDao()
            .insertPlayer(player)

    override fun deletePlayer(id: Long) =
        databaseClientApi
            .playerDao()
            .removePlayer(id)

    override fun getAllPlayers(): LiveData<List<Player>> =
        databaseClientApi
            .playerDao()
            .getAllPlayers()
}