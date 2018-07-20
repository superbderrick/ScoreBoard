package io.github.superbderrick.scoreboard.theme;

import android.graphics.Color;

/**
 * Created by derrick on 03/06/2018.
 */

public abstract class Theme {
    protected int mCurrentCode;
    protected int mUserNameColor;
    protected int mUserNameSummaryColor;
    protected int mScoreColor;
    protected int mSetScoreColor;
    protected int mBarColor;
    protected int mCircleViewColor;
    protected int mBackgroundColor;


    public int getUserNameColor() {
        return mUserNameColor;
    }

    public int getScoreColor() {
        return mScoreColor;
    }

    public int getSetScoreColor() {
        return mSetScoreColor;
    }

    public int getBarColor() {
        return mBarColor;
    }

    public int getCircleViewColor() {
        return mCircleViewColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getUserSummaryColor() {
        return mUserNameSummaryColor;
    }

    abstract void setupThemeColors();
}
