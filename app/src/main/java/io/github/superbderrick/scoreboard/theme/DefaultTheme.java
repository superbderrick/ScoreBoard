package io.github.superbderrick.scoreboard.theme;

import android.graphics.Color;

/**
 * Created by derrick on 03/06/2018.
 */

public class DefaultTheme {
    private String mThemeName;
    private Color mUserNameColor;
    private Color mScoreColor;
    private Color mSetScoreColor;
    private Color mBarColor;
    private Color mCircleViewColor;
    private Color mBackgroundColor;

    public DefaultTheme(String mThemeName, Color mUserNameColor, Color mScoreColor, Color mSetScoreColor, Color mBarColor, Color mCircleViewColor, Color mBackgroundColor) {
        this.mThemeName = mThemeName;
        this.mUserNameColor = mUserNameColor;
        this.mScoreColor = mScoreColor;
        this.mSetScoreColor = mSetScoreColor;
        this.mBarColor = mBarColor;
        this.mCircleViewColor = mCircleViewColor;
        this.mBackgroundColor = mBackgroundColor;
    }

    public String getThemeName() {
        return mThemeName;
    }

    public void setThemeName(String mThemeName) {
        this.mThemeName = mThemeName;
    }

    public Color getUserNameColor() {
        return mUserNameColor;
    }

    public void setUserNameColor(Color mUserNameColor) {
        this.mUserNameColor = mUserNameColor;
    }

    public Color getScoreColor() {
        return mScoreColor;
    }

    public void setScoreColor(Color mScoreColor) {
        this.mScoreColor = mScoreColor;
    }

    public Color getSetScoreColor() {
        return mSetScoreColor;
    }

    public void setSetScoreColor(Color mSetScoreColor) {
        this.mSetScoreColor = mSetScoreColor;
    }

    public Color getBarColor() {
        return mBarColor;
    }

    public void setBarColor(Color mBarColor) {
        this.mBarColor = mBarColor;
    }

    public Color getCircleViewColor() {
        return mCircleViewColor;
    }

    public void setCircleViewColor(Color mCircleViewColor) {
        this.mCircleViewColor = mCircleViewColor;
    }

    public Color getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(Color mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }
}
