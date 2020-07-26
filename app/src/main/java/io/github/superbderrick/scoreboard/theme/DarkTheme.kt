package io.github.superbderrick.scoreboard.theme

import android.graphics.Color

/**
 * Created by derrick on 03/06/2018.
 */
class DarkTheme : Theme() {
    public override fun setupThemeColors() {
        mCurrentCode = 0
        mUserNameBackgroundColor = Color.parseColor("#3F3E43")
        mUserNameTextColor = Color.parseColor("#FB7E4F")
        mUserNameSummaryColor = Color.parseColor("#0E6EB3")
        mCircleViewColor = Color.parseColor("#ff0000")
        mBackgroundColor = Color.parseColor("#ff0000")
        mWholeBackgroundColor = Color.parseColor("#00000f")
        //middle3F3E43
        mJustScoreBackgroundColorString = "#3F3E43"
        mJustScoreTouchBackgroundColorString = "#6A6674"
        mJustScoreTextColor = Color.parseColor("#ffffff")
        mGapColor = Color.parseColor("#79747A")
        mCenterBarColor = Color.parseColor("#79747A")
        //bottom
        mSetScoreTextColor = Color.parseColor("#ffffff")
        mSetScoreBackGroundColor = Color.parseColor("#3F3E43")
        mLeftJustScoreTextColor = Color.parseColor("#53CD6F")
        mRightJustScoreTextColor = Color.parseColor("#FB7E4F")
    }

    init {
        setupThemeColors()
    }
}