package com.aleksejantonov.n2048.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aleksejantonov.n2048.R
import com.aleksejantonov.n2048.di.DI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        DI.componentManager().appComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
