package com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel

import androidx.lifecycle.ViewModel
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.repository.IPlayerRepository
import com.aleksejantonov.n2048.model.Player
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class NewPlayerViewModel @Inject constructor(
    private val playerRepository: IPlayerRepository,
    private val appRouter: AppRouter
) : ViewModel() {

    fun createPlayerAsync(player: Player): Deferred<Unit> {
        return GlobalScope.async {
            playerRepository.createPlayer(player)
        }
    }

    fun onBackPressed() = appRouter.navigateUp()
}