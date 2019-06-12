package com.aleksejantonov.n2048.feature.game.impl.data.repository

import androidx.lifecycle.LiveData
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.model.Player
import javax.inject.Inject

class NewGameRepository @Inject constructor(
    private val databaseClientApi: DatabaseClientApi
) : INewGameRepository {

    override fun observeSelectedPlayer(): LiveData<List<Player>> =
        databaseClientApi.playerDao().observeSelectedPlayer()

    override fun updateScoreAsync(newScore: Long) {
        val currentId = databaseClientApi.playerDao().getSelectedPlayer()?.id
        currentId?.let {
            databaseClientApi.playerDao().updateScore(it, newScore)
        }
    }
}