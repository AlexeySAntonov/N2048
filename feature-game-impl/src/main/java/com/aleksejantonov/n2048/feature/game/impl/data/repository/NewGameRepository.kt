package com.aleksejantonov.n2048.feature.game.impl.data.repository

import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import javax.inject.Inject

class NewGameRepository @Inject constructor(
    private val databaseClientApi: DatabaseClientApi
) : INewGameRepository {

}