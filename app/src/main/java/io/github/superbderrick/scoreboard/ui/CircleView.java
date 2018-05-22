package io.github.superbderrick.scoreboard.ui;

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



public class CircleView extends View {

    private int mCircleColor = Color.parseColor("#ff99cc");

    private final static String CLICKEDCOLOR = "#ff99cc";
    private final static String ORIGINALCOLOR = "#ccebff";
    Paint mPaint = new Paint();

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
        mCircleColor = Color.parseColor(ORIGINALCOLOR);
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
        String tempColor = "#ccebff";
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

        mPaint.setColor(mCircleColor);

        canvas.drawCircle(this.getWidth()/2, 50, 20, mPaint);
    }
}
