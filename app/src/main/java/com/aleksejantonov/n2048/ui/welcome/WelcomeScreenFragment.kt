package com.aleksejantonov.n2048.ui.welcome

import android.os.Bundle
import android.view.View
import com.aleksejantonov.n2048.R
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.di.DI
import kotlinx.android.synthetic.main.fragment_welcome_screen.*

class WelcomeScreenFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_welcome_screen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Test
        newGameButton.setOnClickListener {
            DI.componentManager().getGameFeature().gameStarter().start()
        }
    }
}