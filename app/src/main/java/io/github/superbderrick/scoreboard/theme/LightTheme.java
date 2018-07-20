package io.github.superbderrick.scoreboard.theme;

import android.graphics.Color;

/**
 * Created by derrick on 03/06/2018.
 */

public class LightTheme extends Theme {

    public LightTheme() {
        setupThemeColors();
    }

    @Override
    void setupThemeColors() {
        mCurrentCode = 1;
        mUserNameColor = Color.parseColor("#000000");
        mUserNameSummaryColor = Color.parseColor("#EDEBEC");

        mScoreColor = Color.parseColor("#EDEB00");

        mBarColor = Color.parseColor("#ff0000");
        mCircleViewColor = Color.parseColor("#ff0000");
        mBackgroundColor = Color.parseColor("#ff0000");


        //setscore backgroundview and text color
        mSetScoreTextColor = Color.parseColor("#000000");
        mSetScoreBackGroundColor = Color.parseColor("#D9D9D9");
    }
}
