package com.aleksejantonov.n2048.feature.scores.api.di

import com.aleksejantonov.n2048.feature.scores.api.data.ScoresStarter

interface ScoreFeatureApi {
    fun scoresStarter(): ScoresStarter
}