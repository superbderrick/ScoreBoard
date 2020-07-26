package io.github.superbderrick.scoreboard.ui

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CircleView : View {
    interface OnCircleViewChangeListener {
        fun onTouchedView(score: Int)
    }

    var listener: OnCircleViewChangeListener? = null
    private var mCircleColor = Color.parseColor("#D9D9D9")
    fun setNormalColor(themeValue: Int) {
        if (themeValue == 0) {
            mNormalColor = "#D9D9D9"
        } else if (themeValue == 1) {
            mNormalColor = "#D9D9D9"
        } else {
        }
    }

    private var mNormalColor = "#D9D9D9"
    private var mClickedColor = "#FF1493"
    private val mPaint = Paint()
    private var isTouch = false

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mCircleColor = Color.parseColor(mClickedColor)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setCircleColor(themeColor: Int) {
        if (themeColor == 0) {
            mClickedColor = "#0E6EB3"
        } else if (themeColor == 1) {
            mClickedColor = "#FF1493"
        } else {
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    private fun init() {}
    fun resetCircleViewColor() {
        mCircleColor = Color.parseColor(mNormalColor)
        isTouch = false
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN) {
            setCircleColor()
        }
        return true
    }

    private fun setCircleColor() {
        var tempColor = "#ccebff"
        if (isTouch) {
            tempColor = mNormalColor
            isTouch = false
        } else {
            tempColor = mClickedColor
            isTouch = true
        }
        mCircleColor = Color.parseColor(tempColor)
        invalidate()
        listener!!.onTouchedView(this.id)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = mCircleColor
        canvas.drawCircle(this.width / 2.toFloat(), 50f, 20f, mPaint)
    }

    companion object {
        private val CIRCLEVIEW_ORIGINAL_COLOR: Array<String>
        private val CIRCLEVIEW_CLICKED_COLOR: Array<String>
    }
}