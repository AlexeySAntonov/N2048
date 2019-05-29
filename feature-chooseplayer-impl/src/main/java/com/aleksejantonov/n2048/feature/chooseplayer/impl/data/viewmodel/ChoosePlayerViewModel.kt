package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository.IPlayerRepository
import com.aleksejantonov.n2048.model.Player
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class ChoosePlayerViewModel @Inject constructor(
    private val playerRepository: IPlayerRepository
) : ViewModel() {

    fun createPlayer(player: Player) {
        GlobalScope.async {
            playerRepository.createPlayer(player)
        }
    }

    fun deletePlayer(id: Long) {
        GlobalScope.async {
            playerRepository.deletePlayer(id)
        }
    }

    fun getPlayers(): LiveData<List<Player>> = playerRepository.getAllPlayers()
}