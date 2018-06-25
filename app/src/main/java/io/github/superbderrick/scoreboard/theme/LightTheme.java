package io.github.superbderrick.scoreboard.theme;

import android.graphics.Color;

/**
 * Created by derrick on 03/06/2018.
 */

public class LightTheme extends Theme {

    public LightTheme() {

    }

    @Override
    void setupThemeColors() {
        mUserNameColor = Color.parseColor("#ff0000");
        mUserNameSummaryColor = Color.parseColor("#fff000");
        mScoreColor = Color.parseColor("#fff000");
        mSetScoreColor = Color.parseColor("#f1f000");
        mBarColor = Color.parseColor("#fff0s0");
        mCircleViewColor = Color.parseColor("#f0f000");
        mBackgroundColor = Color.parseColor("#ff0000");
    }
}
