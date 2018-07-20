package io.github.superbderrick.scoreboard.theme;

import android.graphics.Color;

/**
 * Created by derrick on 03/06/2018.
 */

public class DarkTheme extends Theme {

    public DarkTheme() {
        setupThemeColors();
    }

    @Override
    void setupThemeColors() {
        mCurrentCode = 0;
        mUserNameColor = Color.parseColor("#ffff00");
        mScoreColor = Color.parseColor("#ffff00");
        mSetScoreColor = Color.parseColor("#ffff00");
        mBarColor = Color.parseColor("#ffff00");
        mCircleViewColor = Color.parseColor("#ffff00");
        mBackgroundColor = Color.parseColor("#ffff00");
    }


}
