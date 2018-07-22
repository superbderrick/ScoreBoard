package io.github.superbderrick.scoreboard.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;

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

    public static final String PREFS_NAME = "MyPrefsFile";

    private static final int DIRECTION_LEFT = 100;
    private static final int DIRECTION_RIGHT = 200;

    private TouchLayout  mLeftUpperTouchView,mLeftBottomTouchView,mRightUpperTouchView,mRightBottomTouchView;
    private TextView     mLeftScoreTextView,mRightScoreTextView,mLeftSetScoreTextview,mRightSetScoreTextview;
    private EditText     mLeftUserName,mRightUserName;
    private ImageButton  mSettingButton, mResetButton;
    private LinearLayout mLeftScoreLayout,mRightScoreLayout;


    private Handler mMainHandler = new Handler();

    private ScoreManager mScoreManager;

    private SetManager mSetManager;

    private ThemeOperator mThemeOperator;

    private boolean mClickedSettingButton = false;

    private int mLeftSetScore = 0;
    private int mRightSetScore = 0;

    private ArrayList<CircleView> mTempCircleViewArrayList = new ArrayList<CircleView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        setGameListeners();

        initGUIComponent();

        bringSettingValues();

    }

    private void setGameListeners() {
        mScoreManager = new ScoreManager();
        mScoreManager.setScoreMaxRange(ScoreManager.DEFAULT_MAXIMUM_SCORE);
        mScoreManager.setListener(mScoreListener);
        mSetManager = new SetManager();
        mSetManager.setListener(mSetInfoListener);
    }

    private void applyGameTheme(int themeValue) {
        mThemeOperator = new ThemeOperator(themeValue , this);

        mThemeOperator.applyTheme();
    }

    private void bringSettingValues() {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String setCount = SP.getString(this.getResources().getString(R.string.setscore_key),"5");
        String handyValue = SP.getString(this.getResources().getString(R.string.handyy_key),"0");
        String themeValue = SP.getString("themekey" , "1");

        int iThemeValue = Integer.parseInt(themeValue);

        setupSettings(setCount , handyValue , iThemeValue);
    }

    private void setupSettings(String setCount , String handyValue , int themeValue) {
        setSetModule(setCount , themeValue);
        setHandyPoint(handyValue);
        applyGameTheme(themeValue);
    }

    private void setSetModule(final String setCount , final int themeValue) {

        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                int setNum = Integer.parseInt(setCount);
                setupSetCircleView(setNum , themeValue);
                mSetManager.reset();
                mSetManager.setSetNum(setNum);

            }
        });

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


    }

    private void resetUsersName() {
        if(mLeftUserName != null && mRightUserName != null) {
            mLeftUserName.setText("");
            mRightUserName.setText("");
        }
    }

    private void initSetScoreLayout() {
        mLeftSetScoreTextview = findViewById(R.id.leftSetScoreTextview);
        mRightSetScoreTextview = findViewById(R.id.rightsetscoretextview);
    }


    private void initScoreLayout() {
        mLeftScoreLayout = findViewById(R.id.leftScoreLayout);
        mRightScoreLayout = findViewById(R.id.rightScoreLayout);
    }

    private void setupSetCircleView(final int setNum , final int themeValue) {

        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mLeftScoreLayout != null) {
                    makeCircleView(setNum , mLeftScoreLayout ,  DIRECTION_LEFT , themeValue);
                    makeCircleView(setNum , mRightScoreLayout ,  DIRECTION_RIGHT , themeValue);
                }
            }
        });

    }

    private void makeCircleView(int setNum ,LinearLayout layout , int direction , int themeValue) {

        layout.removeAllViews();

        for(int i = 0 ; i < setNum ; i ++) {
            CircleView circleView = new CircleView(MainActivity.this);
            circleView.setCircleColor(themeValue);
            circleView.setNormalColor(themeValue);
            circleView.setId(i+ direction);
            circleView.setListener(mCircleViewListener);

            LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0, 1);
            circleView.setLayoutParams(lp);

            layout.addView(circleView);
            mTempCircleViewArrayList.add(circleView);
        }

    }

    private void initResetButton() {
        mResetButton = findViewById(R.id.timerResetButton);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
                alertbox.setTitle("Reset current game");
                alertbox.setCancelable(false);
                alertbox.setMessage("Do you want to reset all the game score?");
                alertbox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        resetValues();
                        mSetManager.resetTouchValue();
                        for(int i = 0 ; i < mTempCircleViewArrayList.size() ; i++) {
                            mTempCircleViewArrayList.get(i).resetCircleViewColor();
                        }

                    }
                });

                alertbox.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertbox.show();
            }
        });
    }

    private void initSettingButton() {
        mSettingButton = findViewById(R.id.settingButton);
        mSettingButton.bringToFront();
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showDialog(MainActivity.this , "Game Settings" , getString(R.string.gamesetting_guide));

                mClickedSettingButton = true;

                mTempCircleViewArrayList.clear();
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
        mRightUserName = findViewById(R.id.rightUserEdit) ;


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

                mLeftSetScore = left;
                mRightSetScore = right;
            }
        });
    }

    private void resetValues() {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mScoreManager != null && mSetManager != null) {
                    mScoreManager.resetScore();
                }

                resetUsersName();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        saveSetScoreData();
    }

    private void saveSetScoreData() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("leftSetScore", mLeftSetScore);
        editor.putInt("rightSetScore", mRightSetScore);

        editor.commit();

    }

    @Override
    protected void onPause() {
        super.onPause();

            if(mClickedSettingButton) {
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetValues();
                    }
                });
            }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mClickedSettingButton) {
            mClickedSettingButton = false;

            bringSettingValues();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
