package com.aleksejantonov.n2048.feature.chooseplayer.impl

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.ChoosePlayerFeatureComponent
import com.aleksejantonov.n2048.model.Player

class ChoosePlayerFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_choose_player

    private val choosePlayerViewModel by lazy {
        ViewModelProviders.of(this).get(ChoosePlayerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ChoosePlayerFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlayers()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (activity?.isFinishing == true) {
            ChoosePlayerFeatureComponent.release()
            return
        }

        if (isStatedSaved) {
            isStatedSaved = false
            return
        }

        if (isRemoving) {
            ChoosePlayerFeatureComponent.release()
        }
    }

    private fun observePlayers() {
        choosePlayerViewModel
            .getPlayers()
            .observe(
                this,
                Observer<List<Player>> {
                    Toast.makeText(context, "PLAYERS ${it.joinToString()}", Toast.LENGTH_LONG).show()
                }
            )
    }
}