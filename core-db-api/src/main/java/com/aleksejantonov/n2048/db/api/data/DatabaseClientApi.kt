package com.aleksejantonov.n2048.db.api.data

interface DatabaseClientApi {
    fun playerDao(): PlayerDao
}