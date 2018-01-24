package io.github.superbderrick.scoreboard;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mLeftUpperTouchView , mLeftBottomTouchView;

    private View.OnClickListener mLeftUpperTouchListener ,mLeftBottomTouchListener;

    private TextView mLeftScoreTextView;

    private int testValue = 0;

    private Handler mMainHandler = new Handler();

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
    }

    private void initLeftSideComponents() {
        mLeftUpperTouchView = (RelativeLayout)findViewById(R.id.leftUpperTouchView);
        mLeftBottomTouchView = (RelativeLayout)findViewById(R.id.leftBottomTouchView);
        mLeftScoreTextView = (TextView)findViewById(R.id.leftScoreTextview);

        mLeftUpperTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (testValue < 10 || testValue >0) {
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
                        if (testValue >0 || testValue <11 ) {
                            testValue --;
                            mLeftScoreTextView.setText("" +testValue);
                        }
                    }
                });

            }
        });
    }




}
