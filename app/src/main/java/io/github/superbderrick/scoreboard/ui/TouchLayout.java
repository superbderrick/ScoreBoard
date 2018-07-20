package io.github.superbderrick.scoreboard.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


/**
 * Created by derrick on 26/01/2018.
 */

public class TouchLayout extends RelativeLayout {

    private static final String LOG_TAG = "TouchLayout";

    private   String mDefaultBackgroundColor = "#202020";
    private   String mTouchedBackgroundColor = "#111111";

    public String getDefaultBackgroundColor() {
        return mDefaultBackgroundColor;
    }

    public String getTouchedBackgroundColor() {
        return mTouchedBackgroundColor;
    }

    public void setTouchedBackgroundColor(String mTouchedBackgroundColor) {
        this.mTouchedBackgroundColor = mTouchedBackgroundColor;
    }



    public TouchLayout(Context context) {
        super(context);
    }

    public TouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);
        String color = mDefaultBackgroundColor;

        Log.d(LOG_TAG , "onTouchEvent : " + color);
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                color = mTouchedBackgroundColor;
                break;
            case MotionEvent.ACTION_MOVE:
                color = mDefaultBackgroundColor;
                break;
            case MotionEvent.ACTION_UP:
                color = mDefaultBackgroundColor;
                break;
        }

        int finalColor = Color.parseColor(color);

        setBackgroundColor(finalColor);

        return  true;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);

    }
}
