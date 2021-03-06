package com.aleksejantonov.core.navigation.impl.data

import android.os.Bundle
import androidx.navigation.NavController
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.core.navigation.api.data.Screens
import com.aleksejantonov.core.navigation.api.data.Screens.*
import com.aleksejantonov.core.navigation.impl.R

class RouterImpl(private val navigationController: NavController) : AppRouter {

    override fun navigateTo(screen: Screens, args: Bundle?) {
        when (screen) {
            FRAGMENT_GAME          -> navigationController.navigate(
                R.id.action_welcomeScreenFragment_to_newGameFragment,
                args
            )
            FRAGMENT_SCORES        -> navigationController.navigate(
                R.id.action_welcomeScreenFragment_to_scoresFragment,
                args
            )
            FRAGMENT_CHOOSE_PLAYER -> navigationController.navigate(
                R.id.action_welcomeScreenFragment_to_choosePlayerFragment,
                args
            )
            FRAGMENT_NEW_PLAYER    -> navigationController.navigate(
                R.id.action_choosePlayerFragment_to_newPlayerFragment,
                args
            )
        }
    }

    override fun navigateUp() {
        navigationController.navigateUp()
    }
}