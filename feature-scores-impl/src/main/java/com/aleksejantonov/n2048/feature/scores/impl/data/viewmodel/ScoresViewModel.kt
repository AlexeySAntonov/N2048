package com.aleksejantonov.n2048.feature.scores.impl.data.viewmodel


import androidx.lifecycle.ViewModel
import com.aleksejantonov.core.navigation.api.data.AppRouter
import com.aleksejantonov.n2048.feature.scores.impl.data.repository.IScoresRepository
import javax.inject.Inject

class ScoresViewModel @Inject constructor(
    private val scoresRepository: IScoresRepository,
    private val appRouter: AppRouter
) : ViewModel() {

    fun observePlayers() = scoresRepository.observePlayers()

    fun onBackPressed() = appRouter.navigateUp()
}