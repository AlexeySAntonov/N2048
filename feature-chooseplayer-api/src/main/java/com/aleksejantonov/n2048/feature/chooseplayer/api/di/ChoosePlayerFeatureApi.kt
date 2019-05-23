package com.aleksejantonov.n2048.feature.chooseplayer.api.di

import com.aleksejantonov.n2048.feature.chooseplayer.api.data.ChoosePlayerStarter

interface ChoosePlayerFeatureApi {
    fun choosePlayerStarter(): ChoosePlayerStarter
}