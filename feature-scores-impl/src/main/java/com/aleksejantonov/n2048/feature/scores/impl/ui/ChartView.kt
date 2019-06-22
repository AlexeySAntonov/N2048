package com.aleksejantonov.n2048.feature.scores.impl.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.aleksejantonov.n2048.core.ui.base.getScreenWidth
import com.aleksejantonov.n2048.model.Player

class ChartView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    init {
        if (isInEditMode) setPlayers(listOf(Player(name = "Alex")))
    }

    private val screenWidth = context.getScreenWidth()
    private val oval = RectF(screenWidth * 0.2f, screenWidth * 0.2f, screenWidth * 0.8f, screenWidth * 0.8f)
    private var startAngle = 0f
    private var sweepAngle = 0f
    private val paint = Paint().apply {
        color = Color.rgb(CHART_RED, CHART_GREEN, CHART_BLUE)
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.drawArc(oval, startAngle, sweepAngle, true, paint)
        canvas.restore()
    }

    fun setPlayers(players: List<Player>) {
        val pace = 360 / players.size - INTER_ANGLE
        for (player in players) {
            startAngle = sweepAngle + INTER_ANGLE
            sweepAngle += pace
            invalidate()
        }
    }

    companion object {
        private const val CHART_RED = 136
        private const val CHART_GREEN = 125
        private const val CHART_BLUE = 147
        private const val INTER_ANGLE = 10
    }
}