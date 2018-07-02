package io.github.superbderrick.scoreboard.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



public class CircleView extends View {
    public interface OnCircleViewChangeListener {
        public void onTouchedView(int score);
    }

    private OnCircleViewChangeListener mListener;

    private int mCircleColor = Color.parseColor("#ffffff");

    private final static String CLICKEDCOLOR = "#ffffff";
    private final static String ORIGINALCOLOR = "#ccebff";

    private Paint mPaint = new Paint();
    private boolean isTouch = false;

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

    public OnCircleViewChangeListener getListener() {
        return mListener;
    }

    public void setListener(OnCircleViewChangeListener circleListener) {
        this.mListener = circleListener;
    }

    public int getCircleColor() {
        return mCircleColor;
    }

    public void setCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
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
             setCircleColor();
         }

        return true;
    }

    private void setCircleColor() {
        String tempColor = "#ccebff";
        if(isTouch) {
            tempColor = CLICKEDCOLOR;
            isTouch = false;
        } else {
            tempColor = ORIGINALCOLOR;
            isTouch = true;
        }

        mCircleColor = Color.parseColor(tempColor);
        invalidate();
        mListener.onTouchedView(this.getId());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mCircleColor);

        canvas.drawCircle(this.getWidth()/2, 50, 20, mPaint);

    }
}
