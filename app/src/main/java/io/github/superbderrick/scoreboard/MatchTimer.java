package io.github.superbderrick.scoreboard;

/**
 * Created by derrick on 04/02/2018.
 */

public final class MatchTimer {

    private int mTotalTime;

    public interface OnTimerChangeListener {
        public void onTimerStarted();
        public void onTimerEnded();
        public void onTimerChanged();
    }

    public MatchTimer(int totalTime) {
        mTotalTime = totalTime;
    }

    public void Start() {

    }

    public void Reset() {

    }

}
