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

    private int testValue , secondTestValue = 0;

    private Handler mMainHandler = new Handler();

    private EditText mLeftUserName , mRightUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        initGuiComoment();
    }

    private void initGuiComoment() {
        initLeftSideComponents();
        initRightSideComponennts();
    }

    private void initRightSideComponennts() {
        mRightUpperTouchView = (TouchLayout)findViewById(R.id.rightUpperTouchView);
        mRightBottomTouchView = (TouchLayout)findViewById(R.id.rightBottomTouchView);
        mRightScoreTextView = (TextView)findViewById(R.id.rightScoreTextview);

        mRightUpperTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (secondTestValue < 30 && secondTestValue >=0) {
                            secondTestValue ++;

                            mRightScoreTextView.setText("" +secondTestValue);
                        }
                    }
                });


            }
        });
        mRightBottomTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (secondTestValue >0 && secondTestValue <31 ) {
                            secondTestValue --;
                            mRightScoreTextView.setText("" +secondTestValue);
                        }
                    }
                });

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
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (testValue < 30 && testValue >=0) {
                            testValue ++;
                            mLeftScoreTextView.setText("" +testValue);
                        }
                    }
                });


            }
        });
        mLeftBottomTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (testValue >0 && testValue <31 ) {
                            testValue --;
                            mLeftScoreTextView.setText("" +testValue);
                        }
                    }
                });

            }
        });


    }

}
