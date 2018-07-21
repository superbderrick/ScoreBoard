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



        mCircleViewColor = Color.parseColor("#ff0000");
        mBackgroundColor = Color.parseColor("#ff0000");


        // just score background color
        //middle
        mJustScoreBackgroundColorString = "#D9D9D9";
        mJustScoreTouchBackgroundColorString = "#F5F5F5";

        mJustScoreTextColor = Color.parseColor("#000000");
        mGapColor = Color.parseColor("#ffffff");
        mCenterBarColor = Color.parseColor("#00ff00");

        //setscore backgroundview and text color

        //bottom
        mSetScoreTextColor = Color.parseColor("#000000");
        mSetScoreBackGroundColor = Color.parseColor("#D9D9D9");
    }
}
