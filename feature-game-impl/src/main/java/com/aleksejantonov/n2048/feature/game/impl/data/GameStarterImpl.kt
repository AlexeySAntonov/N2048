package com.aleksejantonov.n2048.feature.game.impl.data

import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.data.Screens
import com.aleksejantonov.n2048.feature.game.api.data.GameStarter
import javax.inject.Inject

class GameStarterImpl @Inject constructor(
    private val appRouter: AppRouter
) : GameStarter {

    override fun start() {
        appRouter.navigateTo(Screens.FRAGMENT_GAME)
    }
}