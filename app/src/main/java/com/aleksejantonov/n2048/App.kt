package com.aleksejantonov.n2048

import android.app.Application
import com.aleksejantonov.n2048.di.DI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DI.init(this)
    }
}