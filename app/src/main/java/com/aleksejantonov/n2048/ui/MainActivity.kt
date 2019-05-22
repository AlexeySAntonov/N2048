package com.aleksejantonov.n2048.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.aleksejantonov.n2048.R
import com.aleksejantonov.n2048.di.DI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigationController by lazy {
        NavHostFragment.findNavController(navigationHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DI.componentManager().appComponent(navigationController).inject(this)
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()
}
