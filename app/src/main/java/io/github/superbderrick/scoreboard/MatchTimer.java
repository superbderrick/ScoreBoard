package io.github.superbderrick.scoreboard;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public final class MatchTimer {
    private static final String LOG_TAG = "MatchTimer";

    private int mTotalTime;
    private MatchTimer.OnTimerChangeListener mListener;
    private Timer mInnerTimer;
    private TimerTask mTimerTask;

    public interface OnTimerChangeListener {
        public void onTimerStarted();
        public void onTimerEnded();
        public void onTimerChanged();
    }

    public void setListener(OnTimerChangeListener timerChangeListener) {
        this.mListener = timerChangeListener;
    }

    public MatchTimer(int totalTime) {
        mTotalTime = totalTime;

        initInternal();
    }

    private void initInternal() {
        mInnerTimer = new Timer();

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(LOG_TAG , "Timer ");
            }
        };


    }

    public void startTimer() {
            mInnerTimer.schedule(mTimerTask,0,1000);

    }

    public void resetTimer() {

    }

}
