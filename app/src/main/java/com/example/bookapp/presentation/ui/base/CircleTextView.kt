package com.example.bookapp.presentation.ui.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.bookapp.R

class CircleTextView(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    private val circleColor = ContextCompat.getColor(context, R.color.black)

    override fun draw(canvas: Canvas) {
        val circlePaint = Paint()
        circlePaint.color = circleColor
        circlePaint.flags = Paint.ANTI_ALIAS_FLAG
        canvas.drawCircle(
            (height / 2).toFloat(),
            (height / 2).toFloat(),
            (height / 2).toFloat(),
            circlePaint
        )
        super.draw(canvas)
    }
}