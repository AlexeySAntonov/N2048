package com.aleksejantonov.n2048.feature.game.impl.data

import androidx.navigation.NavController
import com.aleksejantonov.n2048.feature.game.api.data.GameStarter
import javax.inject.Inject

class GameStarterImpl @Inject constructor(
    private val navigationController: NavController
) : GameStarter {

    override fun start() {
        navigationController.navigate()
    }
}