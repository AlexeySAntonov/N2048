package com.aleksejantonov.n2048.feature.scores.impl.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.aleksejantonov.n2048.core.ui.base.getPxFromDp
import com.aleksejantonov.n2048.core.ui.base.getScreenWidth
import com.aleksejantonov.n2048.model.Player

class ChartView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    init {
        if (isInEditMode) setPlayers(listOf(Player(name = "Alex")))
    }

    private val screenWidth = context.getScreenWidth()
    private val oval = RectF()
    private var startAngle = 180f
    private var sweepAngle = 0f
    private val paints = mutableListOf<Paint>()
    private val startAngles = mutableListOf<Float>()
    private val sweepAngles = mutableListOf<Float>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawSectors(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension((screenWidth * 0.6).toInt(), (screenWidth * 0.6).toInt())
    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        oval.set(
            this.left.toFloat() - context.getPxFromDp(RIGHT_LEFT_MARGIN),
            this.top.toFloat() - context.getPxFromDp(TOP_BOTTOM_MARGIN),
            this.right.toFloat() - context.getPxFromDp(RIGHT_LEFT_MARGIN),
            this.bottom.toFloat() - context.getPxFromDp(TOP_BOTTOM_MARGIN)
        )
    }

    private fun drawSectors(canvas: Canvas) {
        for (i in 0 until startAngles.size) {
            canvas.drawArc(oval, startAngles[i], sweepAngles[i], true, paints[i])
        }
    }

    fun setPlayers(players: List<Player>) {
        val scoreSum = players.sumBy { it.score.toInt() }
        val ratio = scoreSum / 360f

        for (i in 0 until players.size) {
            paints.add(
                Paint().apply {
                    isAntiAlias = true
                    color = colors[i % colors.size]
                }
            )

            startAngle += sweepAngle
            sweepAngle = players[i].score / ratio
            startAngles.add(startAngle)
            sweepAngles.add(sweepAngle)
        }

        invalidate()
    }

    companion object {
        private const val TOP_BOTTOM_MARGIN = 72
        private const val RIGHT_LEFT_MARGIN = 48

        private val FIRST_PLACE_COLOR = Color.rgb(229, 115, 115)
        private val SECOND_PLACE_COLOR = Color.rgb(100, 181, 246)
        private val THIRD_PLACE_COLOR = Color.rgb(174, 213, 129)
        private val FOURTH_PLACE_COLOR = Color.rgb(255, 152, 0)
        private val FIFTH_PLACE_COLOR = Color.rgb(136, 125, 147)

        private val colors = arrayOf(
            FIRST_PLACE_COLOR,
            SECOND_PLACE_COLOR,
            THIRD_PLACE_COLOR,
            FOURTH_PLACE_COLOR,
            FIFTH_PLACE_COLOR
        )

    }
}