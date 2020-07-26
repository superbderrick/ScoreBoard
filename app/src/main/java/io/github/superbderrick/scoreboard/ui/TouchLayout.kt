package io.github.superbderrick.scoreboard.ui

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout

/**
 * Created by derrick on 26/01/2018.
 */
class TouchLayout : RelativeLayout {

    private fun setwholeBackGroundColor(i: Int) {
        setBackgroundColor(i)
    }

    private var mDefaultBackgroundColor: String? = "#202020"
    var touchedBackgroundColor: String? = "#111111"
    var defaultBackgroundColor: String?
        get() = mDefaultBackgroundColor
        set(mDefaultBackgroundColor) {
            this.mDefaultBackgroundColor = mDefaultBackgroundColor
            setwholeBackGroundColor(Color.parseColor(this.mDefaultBackgroundColor))
        }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        var color = mDefaultBackgroundColor
        when (event.action) {
            MotionEvent.ACTION_DOWN -> color = touchedBackgroundColor
            MotionEvent.ACTION_MOVE -> color = mDefaultBackgroundColor
            MotionEvent.ACTION_UP -> color = mDefaultBackgroundColor
        }
        setwholeBackGroundColor(Color.parseColor(color))
        return true
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
    }

    companion object {
        private const val LOG_TAG = "TouchLayout"
    }
}