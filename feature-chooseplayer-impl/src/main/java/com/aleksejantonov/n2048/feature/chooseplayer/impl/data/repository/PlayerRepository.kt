package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.model.Player
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val databaseClientApi: DatabaseClientApi
) : IPlayerRepository {

    override fun getAllPlayers(): LiveData<List<Player>> =
        databaseClientApi
            .playerDao()
            .getAllPlayers()
}