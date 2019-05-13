package com.aleksejantonov.n2048.db.api.di

import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi

interface CoreDatabaseApi {
    fun databaseClientApi(): DatabaseClientApi
}