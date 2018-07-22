package io.github.superbderrick.scoreboard.theme;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.superbderrick.scoreboard.R;
import io.github.superbderrick.scoreboard.activities.MainActivity;
import io.github.superbderrick.scoreboard.ui.TouchLayout;

/**
 * Created by derrick on 03/06/2018.
 */

public class ThemeOperator {

    private static final String LOG_TAG = "ThemeOperator";

    private Context mContext = null;
    private Theme mCurrentGameTheme;

    private EditText mLeftUserEditText , mRightUserEditText;

    private TextView mLeftScoreTextView,mRightScoreTextView,mLeftSetScoreTextView,mRightSetScoreTextView;

    private TouchLayout mLeftUpperTouchView , mLeftBottomTouchView , mRightUpperTouchView ,  mRightBottomTouchView;

    private RelativeLayout mLeftCenterBar , mRightCenterBar , mBottomLeftLayout , mBottomRightLayout ,
            mLeftTopBackgroundView , mRightTopBackgroundView , mCenterMiddleLayout;

    private LinearLayout mLeftMiddleLayout , mRightMiddleLayout , mBottomParent;

    private View mCenterBar;

    private ImageButton mSettingButton , mResetButton;

    public ThemeOperator(int themeStyle , Context context) {
        init(themeStyle , context);
    }

    private void init(int theme , Context context) {

        mCurrentGameTheme = ThemeFactory.getTheme(theme);

        mContext = context;

        setupViews(mCurrentGameTheme);
    }

    private void setupViews(Theme theme) {

        //for User EditText
        mLeftUserEditText = ((MainActivity) mContext).findViewById(R.id.leftUserName);
        mRightUserEditText = ((MainActivity) mContext).findViewById(R.id.rightUserEdit);

        //for Score Color
        mLeftScoreTextView = ((MainActivity) mContext).findViewById(R.id.leftScoreTextview);
        mRightScoreTextView = ((MainActivity) mContext).findViewById(R.id.rightScoreTextview);

        //for SetScore Color
        mLeftSetScoreTextView = ((MainActivity) mContext).findViewById(R.id.leftSetScoreTextview);
        mRightSetScoreTextView = ((MainActivity) mContext).findViewById(R.id.rightsetscoretextview);

        mCenterBar = ((MainActivity) mContext).findViewById(R.id.centerbar);


        mLeftUpperTouchView =((MainActivity) mContext).findViewById(R.id.leftUpperTouchView);

        mLeftBottomTouchView =((MainActivity) mContext).findViewById(R.id.leftBottomTouchView);


        mRightUpperTouchView = ((MainActivity) mContext).findViewById(R.id.rightUpperTouchView);
        mRightBottomTouchView =((MainActivity) mContext).findViewById(R.id.rightBottomTouchView);

        mLeftCenterBar = ((MainActivity) mContext).findViewById(R.id.leftBar);
        mRightCenterBar = ((MainActivity) mContext).findViewById(R.id.rightbar);

        //Just Background views

        mLeftTopBackgroundView = ((MainActivity) mContext).findViewById(R.id.topLeftBackground);
        mRightTopBackgroundView = ((MainActivity) mContext).findViewById(R.id.topRightBackground);

        mLeftMiddleLayout = ((MainActivity) mContext).findViewById(R.id.leftScoreLayout);
        mRightMiddleLayout = ((MainActivity) mContext).findViewById(R.id.rightScoreLayout);
        mCenterMiddleLayout = ((MainActivity) mContext).findViewById(R.id.centerParent);

        mBottomLeftLayout = ((MainActivity) mContext).findViewById(R.id.bottomLeftbackgroundview);
        mBottomRightLayout = ((MainActivity) mContext).findViewById(R.id.bottomRightBackgroundView);
        mBottomParent = ((MainActivity) mContext).findViewById(R.id.bottomView);

        mSettingButton = ((MainActivity) mContext).findViewById(R.id.settingButton);
        mResetButton = ((MainActivity) mContext).findViewById(R.id.timerResetButton);
    }

    public void applyTheme() {

        mLeftUserEditText.setBackgroundColor(mCurrentGameTheme.mUserNameBackgroundColor);
        mRightUserEditText.setBackgroundColor(mCurrentGameTheme.mUserNameBackgroundColor);
        mLeftUserEditText.setTextColor(mCurrentGameTheme.mUserNameTextColor);
        mRightUserEditText.setTextColor(mCurrentGameTheme.mUserNameTextColor);
        mLeftUserEditText.setHintTextColor(mCurrentGameTheme.mUserNameSummaryColor);
        mRightUserEditText.setHintTextColor(mCurrentGameTheme.mUserNameSummaryColor);

        setupWholeBackgroundView();

        //setup for bottom line background color for center point
        setupForMiddlePart();

        //setup for bottom line background color for setscore
        setupForBottomLine();


        setupForImageButtons();
    }

    private void setupForImageButtons() {
        if(mCurrentGameTheme.mCurrentCode == 0) {

            mSettingButton.setImageResource(R.drawable.baseline_settings_white_24);
            mResetButton.setImageResource(R.drawable.baseline_refresh_white_24);
        } else if(mCurrentGameTheme.mCurrentCode == 1) {
            mSettingButton.setImageResource(R.drawable.baseline_settings_black_24);
            mResetButton.setImageResource(R.drawable.baseline_refresh_black_24);
        }
    }

    private void setupWholeBackgroundView() {
        mLeftTopBackgroundView.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mRightTopBackgroundView.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mLeftMiddleLayout.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mRightMiddleLayout.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mCenterMiddleLayout.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mBottomRightLayout.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mBottomLeftLayout.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);
        mBottomParent.setBackgroundColor(mCurrentGameTheme.mWholeBackgroundColor);

    }

    private void setupForBottomLine() {
        mLeftSetScoreTextView.setTextColor(mCurrentGameTheme.mSetScoreTextColor);
        mRightSetScoreTextView.setTextColor(mCurrentGameTheme.mSetScoreTextColor);
        mLeftSetScoreTextView.setBackgroundColor(mCurrentGameTheme.mSetScoreBackGroundColor);
        mRightSetScoreTextView.setBackgroundColor(mCurrentGameTheme.mSetScoreBackGroundColor);
    }

    private void setupForMiddlePart() {

        if(mCurrentGameTheme.mCurrentCode  == 0) {
            mLeftScoreTextView.setTextColor(mCurrentGameTheme.mLeftJustScoreTextColor);
            mRightScoreTextView.setTextColor(mCurrentGameTheme.mRightJustScoreTextColor);

        } else if(mCurrentGameTheme.mCurrentCode == 1) {
            mLeftScoreTextView.setTextColor(mCurrentGameTheme.mJustScoreTextColor);
            mRightScoreTextView.setTextColor(mCurrentGameTheme.mJustScoreTextColor);
        }




        mLeftUpperTouchView.setDefaultBackgroundColor(mCurrentGameTheme.mJustScoreBackgroundColorString);
        mLeftBottomTouchView.setDefaultBackgroundColor(mCurrentGameTheme.mJustScoreBackgroundColorString);
        mRightUpperTouchView.setDefaultBackgroundColor(mCurrentGameTheme.mJustScoreBackgroundColorString);
        mRightBottomTouchView.setDefaultBackgroundColor(mCurrentGameTheme.mJustScoreBackgroundColorString);
        mLeftUpperTouchView.setTouchedBackgroundColor(mCurrentGameTheme.mJustScoreTouchBackgroundColorString);
        mLeftBottomTouchView.setTouchedBackgroundColor(mCurrentGameTheme.mJustScoreTouchBackgroundColorString);
        mRightUpperTouchView.setTouchedBackgroundColor(mCurrentGameTheme.mJustScoreTouchBackgroundColorString);
        mRightBottomTouchView.setTouchedBackgroundColor(mCurrentGameTheme.mJustScoreTouchBackgroundColorString);
        mLeftCenterBar.setBackgroundColor(mCurrentGameTheme.mGapColor);
        mRightCenterBar.setBackgroundColor(mCurrentGameTheme.mGapColor);
        mCenterBar.setBackgroundColor(mCurrentGameTheme.mCenterBarColor);

    }

}

 class ThemeFactory {
    public static Theme getTheme(int gameTheme) {

        if(gameTheme > 0) {
            return new LightTheme();
        } else {
            return new DarkTheme();
        }
    }
}
