package io.github.superbderrick.scoreboard.helper;

import android.util.Log;

/**
 * Created by derrick on 27/01/2018.
 */

public class ScoreManager {

    public interface OnScoreChangeListener {
        public void onFirstScoreChanged(int score);
        public void onSecondScoreChanged(int score);
    }

    public enum Operation { Increase, Decrease}
    public enum UserType { First, Second}

    public static final int DEFAULT_MAXIMUM_SCORE = 30;

    private int mFirstScore , mSecondScore;
    private int mScoreMaxRange;
    private OnScoreChangeListener mListener;

    public OnScoreChangeListener getListener() {
        return mListener;
    }

    public void setListener(OnScoreChangeListener mScoreListener) {
        this.mListener = mScoreListener;
    }

    public int getScoreMaxRange() {
        return mScoreMaxRange;
    }
    public void setScoreMaxRange(int mScoreMaxRange) {
        this.mScoreMaxRange = mScoreMaxRange;
    }

    public int getFirstScore() {
        return mFirstScore;
    }

    public int getSecondScore() {
        return mSecondScore;
    }

    public void changeScore(Operation operation , UserType type) {

        if(type == UserType.First) {
            regulateFirstScore(operation);
        } else {
            regulateSecondScore(operation);
        }
    }

    private void regulateFirstScore(Operation operation) {

        if(operation == Operation.Increase) {
            if (mFirstScore < mScoreMaxRange && mFirstScore >=0) {
                mFirstScore ++;
            }
        } else {
            if(mFirstScore > 0 && mFirstScore < mScoreMaxRange+1) {
                mFirstScore --;
            }
        }

        mListener.onFirstScoreChanged(mFirstScore);

    }

    private void regulateSecondScore(Operation operation) {
        if(operation == Operation.Increase) {
            if (mSecondScore < mScoreMaxRange && mSecondScore >=0) {
                mSecondScore ++;
            }
        } else {
            if(mSecondScore > 0 && mSecondScore < mScoreMaxRange+1) {
                mSecondScore --;
            }
        }

        mListener.onSecondScoreChanged(mSecondScore);

    }

    public void resetScore() {
        mFirstScore = 0;
        mSecondScore = 0;
    }


    public ScoreManager() {
        initValues();
    }

    private void initValues() {
        mFirstScore = 0;
        mSecondScore = 0;
    }
}
