package io.github.superbderrick.scoreboard.theme;

/**
 * Created by derrick on 03/06/2018.
 */

public abstract class Theme {
    protected int mCurrentCode;
    protected int mUserNameColor;
    protected int mUserNameSummaryColor;
    protected String mJustScoreBackgroundColorString;
    protected String mJustScoreTouchBackgroundColorString;
    protected int mJustScoreTextColor;
    protected int mSetScoreBackGroundColor;
    protected int mSetScoreTextColor;
    protected int mBarColor;
    protected int mCircleViewColor;
    protected int mBackgroundColor;


    abstract void setupThemeColors();
}
