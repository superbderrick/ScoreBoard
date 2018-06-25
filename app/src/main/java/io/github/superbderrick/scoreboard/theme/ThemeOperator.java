package io.github.superbderrick.scoreboard.theme;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.github.superbderrick.scoreboard.R;
import io.github.superbderrick.scoreboard.activities.MainActivity;

/**
 * Created by derrick on 03/06/2018.
 */

public class ThemeOperator {


    private Context mContext = null;
    private Theme mCurrentGameTheme;

    private EditText mLeftUserEditText , mRightUserEditText;

    private TextView mLeftScoreTextView,mRightScoreTextView,mLeftSetScoreTextView,mRightSetScoreTextView;


    private View mCenterBar;

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

        mCenterBar = ((MainActivity) mContext).findViewById(R.id.middleView);




    }

    public void applyTheme() {
        mLeftUserEditText.setBackgroundColor(mCurrentGameTheme.mUserNameColor);
        mRightUserEditText.setBackgroundColor(mCurrentGameTheme.mUserNameColor);
        mLeftScoreTextView.setBackgroundColor(mCurrentGameTheme.mScoreColor);
        mRightScoreTextView.setBackgroundColor(mCurrentGameTheme.mScoreColor);
        mLeftSetScoreTextView.setBackgroundColor(mCurrentGameTheme.mSetScoreColor);
        mRightSetScoreTextView.setBackgroundColor(mCurrentGameTheme.mSetScoreColor);

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
