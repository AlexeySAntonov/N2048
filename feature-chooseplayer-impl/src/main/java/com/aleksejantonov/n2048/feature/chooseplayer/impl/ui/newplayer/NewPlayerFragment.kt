package com.aleksejantonov.n2048.feature.chooseplayer.impl.ui.newplayer

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aleksejantonov.n2048.core.ui.base.BaseFragment
import com.aleksejantonov.n2048.core.ui.base.TextWatcher
import com.aleksejantonov.n2048.core.ui.base.UiState
import com.aleksejantonov.n2048.feature.chooseplayer.impl.R
import com.aleksejantonov.n2048.feature.chooseplayer.impl.data.viewmodel.NewPlayerViewModel
import com.aleksejantonov.n2048.feature.chooseplayer.impl.di.ChoosePlayerFeatureComponent
import com.aleksejantonov.n2048.model.Player
import kotlinx.android.synthetic.main.fragment_new_player.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class NewPlayerFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_new_player

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newPlayerViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[NewPlayerViewModel::class.java]
    }

    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { _, e ->
            setState(UiState.ERROR)
            Timber.w(e)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ChoosePlayerFeatureComponent.get().inject(this)
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
        observeName()
    }

    override fun showLoading(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun showMessage(succeed: Boolean) {
        var messageId = R.string.create_player_failure
        if (succeed) messageId = R.string.create_player_success
        Toast.makeText(context, messageId, Toast.LENGTH_LONG).show()
    }

    override fun enableControls(enabled: Boolean) {
        createPlayerButton.isEnabled = enabled
        titleInput.isEnabled = enabled
    }

    override fun resetUi() {
        titleInput.setText("")
        createPlayerButton.text = context?.getText(R.string.create_player_again)
    }

    private fun initToolbar() {
        with(toolbar as Toolbar) {
            setTitle(R.string.new_player_toolbar_title)
            setNavigationOnClickListener { newPlayerViewModel.onBackPressed() }
        }
    }

    private fun initViews() {
        with(titleInput) {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    createPlayerButton.callOnClick()
                }
                return@setOnEditorActionListener false
            }
            addTextChangedListener(TextWatcher(newPlayerViewModel::setName))
        }

        createPlayerButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
                setState(UiState.LOADING)
                createPlayerAsync().await()
                setState(UiState.SUCCESS)
            }
        }
    }

    private fun observeName() {
        newPlayerViewModel.observeName()
            .observe(
                this,
                Observer {
                    enableCreateButton(it.isNotBlank())
                }
            )
    }

    private fun createPlayerAsync(): Deferred<Unit> {
        return newPlayerViewModel.createPlayerAsync(Player(name = titleInput.text.toString()))
    }

    private fun enableCreateButton(enabled: Boolean) {
        createPlayerButton.isEnabled = enabled
    }
}