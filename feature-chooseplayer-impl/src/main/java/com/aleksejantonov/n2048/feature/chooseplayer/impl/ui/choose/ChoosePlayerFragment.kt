package com.aleksejantonov.n2048.feature.chooseplayer.impl.ui.choose

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.feature.chooseplayer.impl.R
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel.ChoosePlayerViewModel
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.ChoosePlayerFeatureComponent
import com.aleksejantonov.n2048.feature.chooseplayer.impl.ui.choose.adapter.PlayersAdapter
import com.aleksejantonov.n2048.model.Player
import kotlinx.android.synthetic.main.fragment_choose_player.*
import javax.inject.Inject

class ChoosePlayerFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_choose_player

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val choosePlayerViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ChoosePlayerViewModel::class.java]
    }

    private val adapter by lazy {
        PlayersAdapter(choosePlayerViewModel::deletePlayer, choosePlayerViewModel::selectPlayer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ChoosePlayerFeatureComponent.get().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
        initList()
        observePlayers()
        observeSelectedPlayer()
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

    private fun initToolbar() {
        with(toolbar as Toolbar) {
            setTitle(R.string.choose_player_toolbar_title)
            setNavigationOnClickListener { choosePlayerViewModel.onBackPressed() }
        }
    }

    private fun initViews() {
        newPlayer.setOnClickListener {
            choosePlayerViewModel.openNewPlayerScreen()
        }
    }

    private fun initList() {
        with(recyclerView) {
            adapter = this@ChoosePlayerFragment.adapter
        }
    }

    private fun observePlayers() {
        choosePlayerViewModel.observePlayers()
            .observe(
                this,
                Observer<List<Player>> {
                    adapter.updateItems(it)
                }
            )
    }

    private fun observeSelectedPlayer() {
        choosePlayerViewModel.observeSelectedPlayer()
            .observe(
                this,
                Observer { players ->
                    players.firstOrNull()
                        ?.let {
                            showToast(R.string.choose_player_selected_player, it.name)
                        }
                        ?: showToast(R.string.choose_player_no_one_selected)
                }
            )
    }

    private fun showToast(@StringRes messageId: Int, vararg args: Any) {
        Toast.makeText(context, context?.getString(messageId, *args), Toast.LENGTH_LONG).show()
    }
}