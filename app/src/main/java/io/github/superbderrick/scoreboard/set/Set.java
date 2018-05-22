package io.github.superbderrick.scoreboard.set;

/**
 * Created by derrick on 22/05/2018.
 */

public class Set {
    private boolean mTouched;
    private int mIndex;
    private int mCurrentColor;

    public boolean isTouched() {
        return mTouched;
    }

    public void setTouched(boolean mTouched) {
        this.mTouched = mTouched;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public int getCurrentColor() {
        return mCurrentColor;
    }

    public void setCurrentColor(int mCurrentColor) {
        this.mCurrentColor = mCurrentColor;
    }
}
