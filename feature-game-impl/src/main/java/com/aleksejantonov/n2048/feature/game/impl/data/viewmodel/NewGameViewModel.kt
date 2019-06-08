package com.aleksejantonov.n2048.feature.game.impl.data.viewmodel

import androidx.lifecycle.ViewModel
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.n2048.feature.game.impl.data.repository.INewGameRepository
import javax.inject.Inject

class NewGameViewModel @Inject constructor(
    private val newGameRepository: INewGameRepository,
    private val appRouter: AppRouter
) : ViewModel() {

    fun onBackPressed() = appRouter.navigateUp()
}