package com.aleksejantonov.n2048.feature.scores.impl.data

import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.data.Screens
import com.aleksejantonov.n2048.feature.scores.api.data.ScoresStarter
import javax.inject.Inject

class ScoresStarterImpl @Inject constructor(
    private val appRouter: AppRouter
) : ScoresStarter {

    override fun start() {
        appRouter.navigateTo(Screens.FRAGMENT_SCORES)
    }
}