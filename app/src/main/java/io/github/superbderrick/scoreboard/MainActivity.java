package io.github.superbderrick.scoreboard;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TouchLayout mLeftUpperTouchView ,mLeftBottomTouchView , mRightUpperTouchView , mRightBottomTouchView;

    private TextView mLeftScoreTextView , mRightScoreTextView;


    private Handler mMainHandler = new Handler();

    private EditText mLeftUserName , mRightUserName;

    private ScoreManager mScoreManager;




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

        initGuiComoment();

    }

    private void initGuiComoment() {
        initLeftSideComponents();
        initRightSideComponents();
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




}
