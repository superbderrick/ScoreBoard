package io.github.superbderrick.scoreboard.set;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by derrick on 22/05/2018.
 */

public class SetManager {

    public interface OnSetInfoListener {
        public void onSetInfo(int score , int direction);
    }
    private OnSetInfoListener mListener;

    public void setListener(OnSetInfoListener mSetListener) {
        this.mListener = mSetListener;
    }

    private static final String LOG_TAG = "SetManager";

    private ArrayList<Set> mSetArrayList;
    private ArrayList<Integer> mFinalScoreList;

    private int mSetNum = 0;

    public SetManager() {

        mSetArrayList = new ArrayList<Set>();
        mFinalScoreList = new ArrayList<Integer>();
    }


    public void setSetNum(int setNum) {
        mSetNum = setNum * 2;

        Log.d(LOG_TAG, "inputed size" + mSetNum);
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

    public void setScore(int score) {


        arrangeTouchEvent(score);
        calculateSetScore();
    }

    private void calculateSetScore() {
        int leftScore ,rightScore = 0;
        for(int i = 0 ; i < mSetArrayList.size() ; i ++) {
            Log.d(LOG_TAG , "Touch Value : " + mSetArrayList.get(i).getIndex());
            Log.d(LOG_TAG , "Touch Value : " + mSetArrayList.get(i).isTouched());

            if(i < mSetArrayList.size() / 2) {
                Log.d(LOG_TAG , "current index : " +i);

            } else {

            }
        }
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
        if(mSetArrayList != null && !mSetArrayList.isEmpty()) {
            mSetArrayList.clear();
        }
    }
}
