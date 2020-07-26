package io.github.superbderrick.scoreboard.theme

import android.content.Context
import android.view.View
import android.widget.*
import io.github.superbderrick.scoreboard.R
import io.github.superbderrick.scoreboard.activities.MainActivity
import io.github.superbderrick.scoreboard.ui.TouchLayout

/**
 * Created by derrick on 03/06/2018.
 */
class ThemeOperator(themeStyle: Int, context: Context) {
    private var mContext: Context? = null
    private var mCurrentGameTheme: Theme? = null
    private var mLeftUserEditText: EditText? = null
    private var mRightUserEditText: EditText? = null
    private var mLeftScoreTextView: TextView? = null
    private var mRightScoreTextView: TextView? = null
    private var mLeftSetScoreTextView: TextView? = null
    private var mRightSetScoreTextView: TextView? = null
    private var mLeftUpperTouchView: TouchLayout? = null
    private var mLeftBottomTouchView: TouchLayout? = null
    private var mRightUpperTouchView: TouchLayout? = null
    private var mRightBottomTouchView: TouchLayout? = null
    private var mLeftCenterBar: RelativeLayout? = null
    private var mRightCenterBar: RelativeLayout? = null
    private var mBottomLeftLayout: RelativeLayout? = null
    private var mBottomRightLayout: RelativeLayout? = null
    private var mLeftTopBackgroundView: RelativeLayout? = null
    private var mRightTopBackgroundView: RelativeLayout? = null
    private var mCenterMiddleLayout: RelativeLayout? = null
    private var mLeftMiddleLayout: LinearLayout? = null
    private var mRightMiddleLayout: LinearLayout? = null
    private var mBottomParent: LinearLayout? = null
    private var mCenterBar: View? = null
    private var mSettingButton: ImageButton? = null
    private var mResetButton: ImageButton? = null
    private fun init(theme: Int, context: Context) {
        mCurrentGameTheme = ThemeFactory.getTheme(theme)
        mContext = context
        setupViews(mCurrentGameTheme)
    }

    private fun setupViews(theme: Theme?) { //for User EditText
        mLeftUserEditText = (mContext as MainActivity?)!!.findViewById(R.id.leftUserName)
        mRightUserEditText = (mContext as MainActivity?)!!.findViewById(R.id.rightUserEdit)
        //for Score Color
        mLeftScoreTextView = (mContext as MainActivity?)!!.findViewById(R.id.leftScoreTextview)
        mRightScoreTextView = (mContext as MainActivity?)!!.findViewById(R.id.rightScoreTextview)
        //for SetScore Color
        mLeftSetScoreTextView = (mContext as MainActivity?)!!.findViewById(R.id.leftSetScoreTextview)
        mRightSetScoreTextView = (mContext as MainActivity?)!!.findViewById(R.id.rightsetscoretextview)
        mCenterBar = (mContext as MainActivity?)!!.findViewById(R.id.centerbar)
        mLeftUpperTouchView = (mContext as MainActivity?)!!.findViewById(R.id.leftUpperTouchView)
        mLeftBottomTouchView = (mContext as MainActivity?)!!.findViewById(R.id.leftBottomTouchView)
        mRightUpperTouchView = (mContext as MainActivity?)!!.findViewById(R.id.rightUpperTouchView)
        mRightBottomTouchView = (mContext as MainActivity?)!!.findViewById(R.id.rightBottomTouchView)
        mLeftCenterBar = (mContext as MainActivity?)!!.findViewById(R.id.leftBar)
        mRightCenterBar = (mContext as MainActivity?)!!.findViewById(R.id.rightbar)
        //Just Background views
        mLeftTopBackgroundView = (mContext as MainActivity?)!!.findViewById(R.id.topLeftBackground)
        mRightTopBackgroundView = (mContext as MainActivity?)!!.findViewById(R.id.topRightBackground)
        mLeftMiddleLayout = (mContext as MainActivity?)!!.findViewById(R.id.leftScoreLayout)
        mRightMiddleLayout = (mContext as MainActivity?)!!.findViewById(R.id.rightScoreLayout)
        mCenterMiddleLayout = (mContext as MainActivity?)!!.findViewById(R.id.centerParent)
        mBottomLeftLayout = (mContext as MainActivity?)!!.findViewById(R.id.bottomLeftbackgroundview)
        mBottomRightLayout = (mContext as MainActivity?)!!.findViewById(R.id.bottomRightBackgroundView)
        mBottomParent = (mContext as MainActivity?)!!.findViewById(R.id.bottomView)
        mSettingButton = (mContext as MainActivity?)!!.findViewById(R.id.settingButton)
        mResetButton = (mContext as MainActivity?)!!.findViewById(R.id.timerResetButton)
    }

    fun applyTheme() {
        mLeftUserEditText!!.setBackgroundColor(mCurrentGameTheme!!.mUserNameBackgroundColor)
        mRightUserEditText!!.setBackgroundColor(mCurrentGameTheme!!.mUserNameBackgroundColor)
        mLeftUserEditText!!.setTextColor(mCurrentGameTheme!!.mUserNameTextColor)
        mRightUserEditText!!.setTextColor(mCurrentGameTheme!!.mUserNameTextColor)
        mLeftUserEditText!!.setHintTextColor(mCurrentGameTheme!!.mUserNameSummaryColor)
        mRightUserEditText!!.setHintTextColor(mCurrentGameTheme!!.mUserNameSummaryColor)
        setupWholeBackgroundView()
        //setup for bottom line background color for center point
        setupForMiddlePart()
        //setup for bottom line background color for setscore
        setupForBottomLine()
        setupForImageButtons()
    }

    private fun setupForImageButtons() {
        if (mCurrentGameTheme!!.mCurrentCode == 0) {
            mSettingButton!!.setImageResource(R.drawable.baseline_settings_white_24)
            mResetButton!!.setImageResource(R.drawable.baseline_refresh_white_24)
        } else if (mCurrentGameTheme!!.mCurrentCode == 1) {
            mSettingButton!!.setImageResource(R.drawable.baseline_settings_black_24)
            mResetButton!!.setImageResource(R.drawable.baseline_refresh_black_24)
        }
    }

    private fun setupWholeBackgroundView() {
        mLeftTopBackgroundView!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mRightTopBackgroundView!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mLeftMiddleLayout!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mRightMiddleLayout!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mCenterMiddleLayout!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mBottomRightLayout!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mBottomLeftLayout!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
        mBottomParent!!.setBackgroundColor(mCurrentGameTheme!!.mWholeBackgroundColor)
    }

    private fun setupForBottomLine() {
        mLeftSetScoreTextView!!.setTextColor(mCurrentGameTheme!!.mSetScoreTextColor)
        mRightSetScoreTextView!!.setTextColor(mCurrentGameTheme!!.mSetScoreTextColor)
        mLeftSetScoreTextView!!.setBackgroundColor(mCurrentGameTheme!!.mSetScoreBackGroundColor)
        mRightSetScoreTextView!!.setBackgroundColor(mCurrentGameTheme!!.mSetScoreBackGroundColor)
    }

    private fun setupForMiddlePart() {
        if (mCurrentGameTheme!!.mCurrentCode == 0) {
            mLeftScoreTextView!!.setTextColor(mCurrentGameTheme!!.mLeftJustScoreTextColor)
            mRightScoreTextView!!.setTextColor(mCurrentGameTheme!!.mRightJustScoreTextColor)
        } else if (mCurrentGameTheme!!.mCurrentCode == 1) {
            mLeftScoreTextView!!.setTextColor(mCurrentGameTheme!!.mJustScoreTextColor)
            mRightScoreTextView!!.setTextColor(mCurrentGameTheme!!.mJustScoreTextColor)
        }
        mLeftUpperTouchView.setDefaultBackgroundColor(mCurrentGameTheme!!.mJustScoreBackgroundColorString)
        mLeftBottomTouchView.setDefaultBackgroundColor(mCurrentGameTheme!!.mJustScoreBackgroundColorString)
        mRightUpperTouchView.setDefaultBackgroundColor(mCurrentGameTheme!!.mJustScoreBackgroundColorString)
        mRightBottomTouchView.setDefaultBackgroundColor(mCurrentGameTheme!!.mJustScoreBackgroundColorString)
        mLeftUpperTouchView.setTouchedBackgroundColor(mCurrentGameTheme!!.mJustScoreTouchBackgroundColorString)
        mLeftBottomTouchView.setTouchedBackgroundColor(mCurrentGameTheme!!.mJustScoreTouchBackgroundColorString)
        mRightUpperTouchView.setTouchedBackgroundColor(mCurrentGameTheme!!.mJustScoreTouchBackgroundColorString)
        mRightBottomTouchView.setTouchedBackgroundColor(mCurrentGameTheme!!.mJustScoreTouchBackgroundColorString)
        mLeftCenterBar!!.setBackgroundColor(mCurrentGameTheme!!.mGapColor)
        mRightCenterBar!!.setBackgroundColor(mCurrentGameTheme!!.mGapColor)
        mCenterBar!!.setBackgroundColor(mCurrentGameTheme!!.mCenterBarColor)
    }

    companion object {
        private const val LOG_TAG = "ThemeOperator"
    }

    init {
        init(themeStyle, context)
    }
}

internal object ThemeFactory {
    fun getTheme(gameTheme: Int): Theme {
        return if (gameTheme > 0) {
            LightTheme()
        } else {
            DarkTheme()
        }
    }
}