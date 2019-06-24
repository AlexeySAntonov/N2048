package com.aleksejantonov.n2048.core.ui.base

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.TypedValue
import android.view.View

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun Context.getScreenWidth(): Int {
    val size = Point()
    (this as Activity).windowManager.defaultDisplay.getSize(size)
    return size.x
}

fun Context.getPxFromDp(dpValue: Int): Int =
    TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), resources.displayMetrics)
        .toInt()