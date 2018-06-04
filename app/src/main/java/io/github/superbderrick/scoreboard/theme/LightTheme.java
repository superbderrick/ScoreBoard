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
        mUserNameColor = Color.parseColor("#fff000");
        mUserNameSummaryColor = Color.parseColor("#fff000");
        mScoreColor = Color.parseColor("#fff000");
        mSetScoreColor = Color.parseColor("#fff000");
        mBarColor = Color.parseColor("#fff000");
        mCircleViewColor = Color.parseColor("#fff000");
        mBackgroundColor = Color.parseColor("#fff000");
    }
}
