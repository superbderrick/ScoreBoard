package io.github.superbderrick.scoreboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    //GuiComponents.
    private TouchLayout mLeftUpperTouchView ,mLeftBottomTouchView , mRightUpperTouchView , mRightBottomTouchView;
    private TextView mLeftScoreTextView , mRightScoreTextView;
    private EditText mLeftUserName , mRightUserName;
    private ImageButton mSettingButton , mTimerButton , mTimerResetButton;

    private Handler mMainHandler = new Handler();

    private ScoreManager mScoreManager;
    private MatchTimer mMatchTimer;


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

        mMatchTimer = new MatchTimer(100);
        mMatchTimer.setListener(mMatchTimerListener);

        setSettingValues();
        initGuiComoment();

    }

    private void setSettingValues() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String setCount = SP.getString(this.getResources().getString(R.string.setscore_key),"1");
        String gameTime = SP.getString(this.getResources().getString(R.string.gametime_key),"1");
    }

    private void initGuiComoment() {
        initLeftSideComponents();
        initRightSideComponents();
        initSettingButton();
        initTimerButton();
        initResetButton();
    }

    private void initResetButton() {
        mTimerResetButton = (ImageButton)findViewById(R.id.timerResetButton);
        mTimerResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG , "Reset button clicked ");
            }
        });
    }

    private void initTimerButton() {
        mTimerButton = (ImageButton)findViewById(R.id.timerButton);

        final String startTag =  getString(R.string.timer_start);
        final String pauseTag =  getString(R.string.timer_pause);
        final int pauseImage = R.drawable.ic_pause_circle_outline_white_24dp;
        final int startImage = R.drawable.ic_play_circle_outline_white_24dp;

        mTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int tempImage = startImage;
                if(mTimerButton.getTag().equals(startTag)) {
                    tempImage = pauseImage;
                    mTimerButton.setTag(pauseTag);

                    mMatchTimer.startTimer();

                } else {
                    tempImage = startImage;
                    mTimerButton.setTag(startTag);
                }

                final int finalTempImage = tempImage;
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        final int timerImage = finalTempImage;
                        mTimerButton.setImageResource(timerImage);
                    }
                });


            }
        });
    }

    private void initSettingButton() {
        mSettingButton = (ImageButton)findViewById(R.id.settingButton);
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , SettingActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRightSideComponents() {
        mRightUpperTouchView = (TouchLayout)findViewById(R.id.rightUpperTouchView);
        mRightBottomTouchView = (TouchLayout)findViewById(R.id.rightBottomTouchView);
        mRightScoreTextView = (TextView)findViewById(R.id.rightScoreTextview);

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
        mLeftUpperTouchView = (TouchLayout) findViewById(R.id.leftUpperTouchView);
        mLeftBottomTouchView = (TouchLayout)findViewById(R.id.leftBottomTouchView);
        mLeftScoreTextView = (TextView)findViewById(R.id.leftScoreTextview);
        mLeftUserName = (EditText)findViewById(R.id.leftUserName) ;


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
    };

    MatchTimer.OnTimerChangeListener mMatchTimerListener = new MatchTimer.OnTimerChangeListener() {
        @Override
        public void onTimerStarted() {

        }

        @Override
        public void onTimerEnded() {

        }

        @Override
        public void onTimerChanged() {

        }
    };


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        @SuppressLint("ResourceType") String setCount = SP.getString(String.valueOf(this.getResources().getDrawable(R.string.setscore_key)),"1");
        @SuppressLint("ResourceType") String gameTime = SP.getString(String.valueOf(this.getResources().getDrawable(R.string.gametime_key)),"1");


        Log.d("Derrick" , "setCount value : " + setCount);
        Log.d("Derrick" , "gameTime value : " + gameTime);

    }


}
