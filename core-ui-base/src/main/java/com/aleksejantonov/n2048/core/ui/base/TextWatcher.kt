package com.aleksejantonov.n2048.core.ui.base

import android.text.Editable
import android.text.TextWatcher

class TextWatcher(private val listener: (String) -> Unit) : TextWatcher {
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        listener.invoke(s.toString())
    }

    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
}