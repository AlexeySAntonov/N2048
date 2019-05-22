package com.aleksejantonov.n2048.db.impl.di

import android.content.Context
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
@Singleton
interface DatabaseComponent : CoreDatabaseApi {

    companion object {
        private var databaseComponent: DatabaseComponent? = null

        fun initAndGet(context: Context): CoreDatabaseApi {
            if (databaseComponent == null) {
                databaseComponent = DaggerDatabaseComponent.builder()
                    .databaseModule(DatabaseModule(context))
                    .build()
            }
            return requireNotNull(databaseComponent)
        }

        fun get(): CoreDatabaseApi = requireNotNull(databaseComponent)
    }
}