package io.github.superbderrick.scoreboard.theme

/**
 * Created by derrick on 03/06/2018.
 */
abstract class Theme {
    var mCurrentCode = 0
    var mUserNameBackgroundColor = 0
    var mUserNameTextColor = 0
    var mUserNameSummaryColor = 0
    var mJustScoreBackgroundColorString: String? = null
    var mJustScoreTouchBackgroundColorString: String? = null
    var mJustScoreTextColor = 0
    var mLeftJustScoreTextColor = 0
    var mRightJustScoreTextColor = 0
    var mSetScoreBackGroundColor = 0
    var mSetScoreTextColor = 0
    var mGapColor = 0
    protected var mCircleViewColor = 0
    protected var mBackgroundColor = 0
    var mCenterBarColor = 0
    var mWholeBackgroundColor = 0
    abstract fun setupThemeColors()
}