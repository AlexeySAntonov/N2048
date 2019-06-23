package com.aleksejantonov.n2048.feature.scores.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.model.Player
import javax.inject.Inject

class ScoresRepository @Inject constructor(
    private val databaseClientApi: DatabaseClientApi
) : IScoresRepository {

    override fun observePlayers(): LiveData<List<Player>> =
        databaseClientApi.playerDao().getBestPlayers()
}