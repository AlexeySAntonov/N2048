package com.aleksejantonov.n2048.feature.chooseplayer.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository.IPlayerRepository
import com.aleksejantonov.n2048.model.Player
import javax.inject.Inject

class ChoosePlayerViewModel @Inject constructor(
    private val playerRepository: IPlayerRepository
) : ViewModel() {

    fun getPlayers(): LiveData<List<Player>> = playerRepository.getAllPlayers()
}