package io.github.superbderrick.scoreboard.set;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by derrick on 22/05/2018.
 */

public class SetManager {

    public interface OnSetInfoListener {
        public void onSetInfo(int [] scoreArray);
        public void onSetInitialized();
    }
    private OnSetInfoListener mListener;

    public void setListener(OnSetInfoListener mSetListener) {
        this.mListener = mSetListener;
    }

    private static final String LOG_TAG = "SetManager";

    private ArrayList<Set> mSetArrayList;
    private int [] mFinalScoreList;
    private int mLeftScore , mRightScore = 0;

    private int mSetNum = 0;

    public SetManager() {

        mSetArrayList = new ArrayList<Set>();
        mFinalScoreList = new int[2];
    }


    public void setSetNum(int setNum) {
        mSetNum = setNum * 2;

        makeSetArray(mSetNum);
    }

    private void makeSetArray(int mSetNum) {
        for(int i = 0 ; i< mSetNum ; i++) {
            Set set = new Set();
            set.setIndex(i);
            set.setTouched(false);
            mSetArrayList.add(set);
        }
    }

    public void resetTouchValue() {
        if(mSetArrayList != null) {
            for(int i = 0 ; i < mSetArrayList.size(); i++) {
              mSetArrayList.get(i).setTouched(false);
            }

            mLeftScore = 0;
            mRightScore = 0;

            mFinalScoreList[0] = 0;
            mFinalScoreList[1] = 0;

            mListener.onSetInfo(mFinalScoreList);
        }
    }

    public void setScore(int score) {

        arrangeTouchEvent(score);

        final int [] finalArrayList = calculateSetScore();

        mLeftScore = 0;
        mRightScore = 0;

        mListener.onSetInfo(finalArrayList);

    }

    private int[] calculateSetScore() {

        for(int i = 0 ; i < mSetArrayList.size() ; i ++) {
            if(i < mSetArrayList.size() / 2) {
                if(mSetArrayList.get(i).isTouched() == true) {
                    mLeftScore ++;
                }

            } else {
                if(mSetArrayList.get(i).isTouched() == true) {
                    mRightScore ++;
                }

            }

        }
        mFinalScoreList[0] = mLeftScore;
        mFinalScoreList[1] = mRightScore;

        return  mFinalScoreList;
    }

    private void arrangeTouchEvent(int score) {
        int index = 0;
        if(score < 200 ) {
             index = score - 100 ;
        } else {
            if(mSetArrayList != null) {
                index = score - 200  ;

                if(index == 0) {
                    index = mSetArrayList.size() / 2;
                } else {
                    index = index + mSetArrayList.size() /2 ;
                }
            }
        }

        if(mSetArrayList.get(index).isTouched() == false) {
            mSetArrayList.get(index).setTouched(true);
        } else {
            mSetArrayList.get(index).setTouched(false);
        }

    }


    public void reset() {
        Log.d(LOG_TAG , "reset is called ");
        if(mSetArrayList != null && !mSetArrayList.isEmpty()) {
            mSetArrayList.clear();
        }

        mLeftScore = 0;
        mRightScore = 0;
        mListener.onSetInitialized();
    }
}
