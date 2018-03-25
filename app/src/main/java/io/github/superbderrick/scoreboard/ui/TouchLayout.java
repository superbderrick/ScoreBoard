package io.github.superbderrick.scoreboard.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * Created by derrick on 26/01/2018.
 */

public class TouchLayout extends RelativeLayout {

    private  final  static String DEFAULTCOLOR = "#202020";
    private  final  static String TOUCHEDCOLOR = "#111111";

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
        String color = DEFAULTCOLOR;
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                color = TOUCHEDCOLOR;
                break;
            case MotionEvent.ACTION_MOVE:
                color = DEFAULTCOLOR;
                break;
            case MotionEvent.ACTION_UP:
                color = DEFAULTCOLOR;
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
