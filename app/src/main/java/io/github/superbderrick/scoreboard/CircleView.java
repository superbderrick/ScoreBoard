package io.github.superbderrick.scoreboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by derrick on 07/01/2018.
 */

public class CircleView extends View {

    private int mCircleColor = Color.parseColor("#ff99cc");

    private final static String ORIGINALCOLOR = "#ff99cc";
    private final static String CLICKEDCOLOR = "#ccebff";

    public boolean isTouch() {
        return isTouch;
    }

    private boolean isTouch = false;

    public int getmCircleColor() {
        return mCircleColor;
    }

    public void setmCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
    }

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCircleColor = context.obtainStyledAttributes(attrs, R.styleable.NewAttr)
                .getColor(R.styleable.NewAttr_circleColor, Color.WHITE);

    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);

         if (event.getAction() == MotionEvent.ACTION_DOWN) {
             setCircleColer();
         }



        return true;
    }

    private void setCircleColer() {
        String tempColor = ORIGINALCOLOR;
        if(isTouch) {
            tempColor = ORIGINALCOLOR;
            isTouch = false;
        } else {
            tempColor = CLICKEDCOLOR;
            isTouch = true;
        }

        mCircleColor = Color.parseColor(tempColor);
        invalidate();
    }

    //Draw APIS.

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(mCircleColor);

        canvas.drawCircle(this.getWidth()/2, 50, 20, paint);

    }
}
