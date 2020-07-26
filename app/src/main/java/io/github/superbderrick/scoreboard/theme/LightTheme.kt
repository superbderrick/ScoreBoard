package io.github.superbderrick.scoreboard.theme

import android.graphics.Color

/**
 * Created by derrick on 03/06/2018.
 */
class LightTheme : Theme() {
    public override fun setupThemeColors() {
        mCurrentCode = 1
        mUserNameBackgroundColor = Color.parseColor("#D9D9D9")
        mUserNameTextColor = Color.parseColor("#000000")
        mUserNameSummaryColor = Color.parseColor("#FF1493")
        mCircleViewColor = Color.parseColor("#ff0000")
        mBackgroundColor = Color.parseColor("#ff0000")
        mWholeBackgroundColor = Color.parseColor("#FFFEFF")
        //middle
        mJustScoreBackgroundColorString = "#D9D9D9"
        mJustScoreTouchBackgroundColorString = "#F5F5F5"
        mJustScoreTextColor = Color.parseColor("#000000")
        mGapColor = Color.parseColor("#ffffff")
        mCenterBarColor = Color.parseColor("#000000")
        //bottom
        mSetScoreTextColor = Color.parseColor("#000000")
        mSetScoreBackGroundColor = Color.parseColor("#D9D9D9")
        mLeftJustScoreTextColor = mJustScoreTextColor
        mRightJustScoreTextColor = mJustScoreTextColor
    }

    init {
        setupThemeColors()
    }
}