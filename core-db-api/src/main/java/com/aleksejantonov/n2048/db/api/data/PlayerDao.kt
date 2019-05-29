package com.aleksejantonov.n2048.db.api.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksejantonov.n2048.model.Player
import io.reactivex.Single

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Query("SELECT * FROM player WHERE id = :id")
    fun getPlayer(id: Long): Single<Player>

    @Query("SELECT * FROM player")
    fun getAllPlayers(): LiveData<List<Player>>

    @Query("DELETE FROM player WHERE id = :id")
    fun removePlayer(id: Long)
}