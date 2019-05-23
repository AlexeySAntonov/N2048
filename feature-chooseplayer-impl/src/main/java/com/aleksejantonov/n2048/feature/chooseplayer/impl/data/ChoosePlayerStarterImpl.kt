package com.aleksejantonov.n2048.feature.chooseplayer.impl.data

import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.data.Screens
import com.aleksejantonov.n2048.feature.chooseplayer.api.data.ChoosePlayerStarter
import javax.inject.Inject

class ChoosePlayerStarterImpl @Inject constructor(
    private val appRouter: AppRouter
) : ChoosePlayerStarter {

    override fun start() {
        appRouter.navigateTo(Screens.FRAGMENT_CHOOSE_PLAYER)
    }
}