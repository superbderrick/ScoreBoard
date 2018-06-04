package io.github.superbderrick.scoreboard.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.superbderrick.scoreboard.R;
import io.github.superbderrick.scoreboard.helper.ScoreManager;
import io.github.superbderrick.scoreboard.set.SetManager;
import io.github.superbderrick.scoreboard.settings.Handy;
import io.github.superbderrick.scoreboard.settings.HandyCalculator;
import io.github.superbderrick.scoreboard.theme.ThemeOperator;
import io.github.superbderrick.scoreboard.ui.CircleView;
import io.github.superbderrick.scoreboard.ui.TouchLayout;
import io.github.superbderrick.scoreboard.ui.Utils;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    private static final int DIRECTION_LEFT = 100;
    private static final int DIRECTION_RIGHT = 200;

    private TouchLayout  mLeftUpperTouchView,mLeftBottomTouchView,mRightUpperTouchView,mRightBottomTouchView;
    private TextView     mLeftScoreTextView,mRightScoreTextView,mLeftSetScoreTextview,mRightSetScoreTextview;
    private EditText     mLeftUserName,mRightUserName;
    private ImageButton  mSettingButton,mTimerResetButton;
    private LinearLayout mLeftScoreLayout,mRightScoreLayout;

    private View mCenterBar;

    private Handler mMainHandler = new Handler();

    private ScoreManager mScoreManager;

    private SetManager mSetManager;

    private ThemeOperator mThemeOperator;

    private boolean mIsResetValue = false;
    private int mThemeValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        mScoreManager = new ScoreManager();
        mScoreManager.setScoreMaxRange(ScoreManager.DEFAULT_MAXIMUM_SCORE);
        mScoreManager.setListener(mScoreListener);
        mSetManager = new SetManager();
        mSetManager.setListener(mSetInfoListener);

        bringSettingValues();

        initGUIComponent();

        applyGameTheme();
    }

    private void applyGameTheme() {
        mThemeOperator = new ThemeOperator(mThemeValue , this);
        mThemeOperator.applyTheme();
    }

    private void bringSettingValues() {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String setCount = SP.getString(this.getResources().getString(R.string.setscore_key),"1");
        String handyValue = SP.getString(this.getResources().getString(R.string.handyy_key),"1");

        String themeValue = SP.getString("themekey" , "1");
        mThemeValue = Integer.parseInt(themeValue);


        setupSettings(setCount , handyValue);
    }

    private void setupSettings(String setCount , String handyValue) {
        setSetModule(setCount);
        setHandyPoint(handyValue);

    }

    private void setSetModule(String setCount) {
        int setNum = Integer.parseInt(setCount);
        setupSetCircleView(setNum);
        mSetManager.reset();
        mSetManager.setSetNum(setNum);
    }

    private void setHandyPoint(String handyValue) {
        final Handy handy = HandyCalculator.getHandy(Integer.parseInt(handyValue));
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {

                if(handy.getDirection() < Handy.RIGHT_USER) {
                    // left
                    mScoreManager.changeScore(ScoreManager.Operation.Increase , ScoreManager.UserType.First,handy.getHandyPoint());
                } else {
                    // right
                    mScoreManager.changeScore(ScoreManager.Operation.Increase , ScoreManager.UserType.Second,handy.getHandyPoint());
                }

            }
        });
    }

    private void initGUIComponent() {
        initLeftSideComponents();
        initRightSideComponents();
        initSettingButton();
        initResetButton();
        initScoreLayout();
        initSetScoreLayout();
        initCenterBar();
    }

    private void initSetScoreLayout() {
        mLeftSetScoreTextview = findViewById(R.id.leftSetScoreTextview);
        mRightSetScoreTextview = findViewById(R.id.rightsetscoretextview);
    }

    private void initCenterBar() {
        mCenterBar = findViewById(R.id.middleView);
    }

    private void initScoreLayout() {
        mLeftScoreLayout = findViewById(R.id.leftScoreLayout);
        mRightScoreLayout = findViewById(R.id.rightScoreLayout);
    }

    private void setupSetCircleView(final int setNum) {

        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mLeftScoreLayout != null) {
                    makeCircleView(setNum , mLeftScoreLayout ,  DIRECTION_LEFT);
                    makeCircleView(setNum , mRightScoreLayout ,  DIRECTION_RIGHT);
                }
            }
        });

    }

    private void makeCircleView(int setNum ,LinearLayout layout , int direction) {

        layout.removeAllViews();

        for(int i = 0 ; i < setNum ; i ++) {
            CircleView circleView = new CircleView(MainActivity.this);
            circleView.setId(i+ direction);
            circleView.setListener(mCircleViewListener);

            LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0, 1);
            circleView.setLayoutParams(lp);

            layout.addView(circleView);
        }

    }

    private void initResetButton() {
        mTimerResetButton = findViewById(R.id.timerResetButton);
        mTimerResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG , "Reset button clicked ");
            }
        });
    }

    private void initSettingButton() {
        mSettingButton = findViewById(R.id.settingButton);
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showDialog(MainActivity.this , "Game Settings" , getString(R.string.gamesetting_guide));

                mIsResetValue = true;
            }
        });
    }

    private void initRightSideComponents() {
        mRightUpperTouchView = findViewById(R.id.rightUpperTouchView);
        mRightBottomTouchView = findViewById(R.id.rightBottomTouchView);
        mRightScoreTextView = findViewById(R.id.rightScoreTextview);

        mRightUpperTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScoreManager.changeScore(ScoreManager.Operation.Increase , ScoreManager.UserType.Second);
            }
        });
        mRightBottomTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mScoreManager.changeScore(ScoreManager.Operation.Decrease , ScoreManager.UserType.Second);
            }
        });
    }

    private void initLeftSideComponents() {
        mLeftUpperTouchView =  findViewById(R.id.leftUpperTouchView);
        mLeftBottomTouchView = findViewById(R.id.leftBottomTouchView);
        mLeftScoreTextView = findViewById(R.id.leftScoreTextview);
        mLeftUserName = findViewById(R.id.leftUserName) ;


        mLeftUpperTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScoreManager.changeScore(ScoreManager.Operation.Increase , ScoreManager.UserType.First);

            }
        });
        mLeftBottomTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mScoreManager.changeScore(ScoreManager.Operation.Decrease , ScoreManager.UserType.First);
            }
        });


    }

    ScoreManager.OnScoreChangeListener mScoreListener = new ScoreManager.OnScoreChangeListener() {
        @Override
        public void onFirstScoreChanged(final int score) {

            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String finalScore = "" + score;
                    mLeftScoreTextView.setText(finalScore);
                }
            });

        }

        @Override
        public void onSecondScoreChanged(final int score) {
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String finalScore = "" + score;
                    mRightScoreTextView.setText(finalScore);
                }
            });
        }

        @Override
        public void onScoreInitialized(final int score) {
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String finalScore = "" + score;
                    mRightScoreTextView.setText(finalScore);
                    mLeftScoreTextView.setText(finalScore);
                }
            });
        }
    };

    CircleView.OnCircleViewChangeListener mCircleViewListener = new CircleView.OnCircleViewChangeListener() {
        @Override
        public void onTouchedView(int id) {
            Log.d(LOG_TAG , "ID: " + id);
            if(mSetManager != null) {
                mSetManager.setScore(id);
            }
        }
    };

    SetManager.OnSetInfoListener mSetInfoListener = new SetManager.OnSetInfoListener() {

        @Override
        public void onSetInfo(int [] scores) {

            setSetScoreLayout(scores[0] , scores[1]);
        }

        @Override
        public void onSetInitialized() {
            setSetScoreLayout(0 ,0);
        }
    };

    private void setSetScoreLayout(final int left , final int right) {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {

                mLeftSetScoreTextview.setText("" + left);
                mRightSetScoreTextview.setText("" + right);
            }
        });
    }

    private void resetValues() {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mScoreManager != null && mSetManager != null) {
                    mScoreManager.resetScore();
                    //mSetManager.reset();
                }

            }
        });


    }
    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(LOG_TAG,"check onRestart is called" + mIsResetValue);
        bringSettingValues();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG,"check onPause is called" + mIsResetValue);

        if(mIsResetValue)
            resetValues();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mIsResetValue = false;
        Log.d(LOG_TAG,"check onResume is called");
    }

}
