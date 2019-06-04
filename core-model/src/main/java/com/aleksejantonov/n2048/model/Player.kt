package com.aleksejantonov.n2048.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class Player(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var score: Long = 0,
    var isSelected: Boolean = false
)