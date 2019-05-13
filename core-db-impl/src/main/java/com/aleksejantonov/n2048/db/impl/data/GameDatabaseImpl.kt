package com.aleksejantonov.n2048.db.impl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.model.Player

@Database(entities = [Player::class], version = 1)
abstract class GameDatabaseImpl : RoomDatabase(), DatabaseClientApi