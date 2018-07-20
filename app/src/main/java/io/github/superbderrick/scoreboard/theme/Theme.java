package io.github.superbderrick.scoreboard.theme;

/**
 * Created by derrick on 03/06/2018.
 */

public abstract class Theme {
    protected int mCurrentCode;
    protected int mUserNameColor;
    protected int mUserNameSummaryColor;


    protected int mJustScoreBackgroundColor;
    protected int mJustScoreTextColor;

    protected int mSetScoreBackGroundColor;
    protected int mSetScoreTextColor;

    protected int mBarColor;
    protected int mCircleViewColor;
    protected int mBackgroundColor;

    public int getSetScoreTextColor() {
        return mSetScoreTextColor;
    }

    public void setSetScoreTextColor(int mSetScoreTextColor) {
        this.mSetScoreTextColor = mSetScoreTextColor;
    }

    public int getUserNameColor() {
        return mUserNameColor;
    }

    public int getScoreColor() {
        return mJustScoreBackgroundColor;
    }

    public int getSetScoreColor() {
        return mSetScoreBackGroundColor;
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
