package com.aleksejantonov.n2048.db.impl.di

import android.content.Context
import com.aleksejantonov.n2048.db.api.di.CoreDatabaseApi
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [DatabaseModule::class],
    dependencies = [DatabaseComponentDependencies::class]
)
@Singleton
interface DatabaseComponent : CoreDatabaseApi {

}

interface DatabaseComponentDependencies {
    fun context(): Context
}