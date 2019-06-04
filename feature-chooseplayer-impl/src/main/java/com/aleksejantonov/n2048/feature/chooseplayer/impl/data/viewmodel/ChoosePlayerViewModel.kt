package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.data.Screens
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository.IPlayerRepository
import com.aleksejantonov.n2048.model.Player
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class ChoosePlayerViewModel @Inject constructor(
    private val playerRepository: IPlayerRepository,
    private val appRouter: AppRouter
) : ViewModel() {

    fun deletePlayer(id: Long) {
        GlobalScope.async {
            playerRepository.deletePlayer(id)
        }
    }

    fun selectPlayer(id: Long) {
        GlobalScope.async {
            playerRepository.selectPlayer(id)
        }
    }

    fun observePlayers(): LiveData<List<Player>> = playerRepository.observeAllPlayers()

    fun observeSelectedPlayer(): LiveData<List<Player>> = playerRepository.observeSelectedPlayer()

    fun openNewPlayerScreen() = appRouter.navigateTo(Screens.FRAGMENT_NEW_PLAYER)

    fun onBackPressed() = appRouter.navigateUp()
}