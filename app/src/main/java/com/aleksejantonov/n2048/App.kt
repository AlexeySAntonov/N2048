package com.aleksejantonov.n2048

import android.app.Application
import com.aleksejantonov.n2048.di.DI
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DI.init(this)
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}