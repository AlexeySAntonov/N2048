package com.aleksejantonov.n2048.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layoutId: Int

    open fun showLoading(loading: Boolean) = Unit

    open fun showMessage(succeed: Boolean) = Unit

    open fun enableControls(enabled: Boolean) = Unit

    open fun resetUi() = Unit

    protected var isStatedSaved: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(context).inflate(layoutId, container, false)
    }

    override fun onStart() {
        super.onStart()
        isStatedSaved = false
    }

    override fun onResume() {
        super.onResume()
        isStatedSaved = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isStatedSaved = true
    }

    fun setState(state: UiState) {
        when (state) {
            UiState.LOADING -> {
                showLoading(true)
                enableControls(false)
            }
            UiState.SUCCESS -> {
                showMessage(true)
                showLoading(false)
                enableControls(true)
                resetUi()
            }
            UiState.ERROR   -> {
                showMessage(false)
                showLoading(false)
                enableControls(false)
            }
        }
    }
}