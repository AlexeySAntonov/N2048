package com.aleksejantonov.n2048.feature.game.api.di

import com.aleksejantonov.n2048.feature.game.api.data.GameStarter

interface GameFeatureApi {
    fun gameStarter(): GameStarter
}