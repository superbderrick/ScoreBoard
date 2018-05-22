package io.github.superbderrick.scoreboard.set;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by derrick on 22/05/2018.
 */

public class SetManager {
    private static final String LOG_TAG = "SetManager";

    private ArrayList<Set> mSetArrayList;
    private int mSetNum = 0;

    public SetManager() {

        mSetArrayList = new ArrayList<Set>();
    }

    public int getSetNum() {
        return mSetNum;
    }

    public void setSetNum(int mSetNum) {
        this.mSetNum = mSetNum;

        makeSetArray(mSetNum);
    }

    private void makeSetArray(int mSetNum) {
        for(int i = 0 ; i< mSetNum ; i++) {
            Set set = new Set();
            mSetArrayList.add(set);
        }
    }

    public void setScore(int score) {

        Log.d(LOG_TAG , "score " + score);

        int index = 0;
        if(score < 200 ) {
            //left
            //100 0
            //102 1
            //103 2
             index = score - 100 ;


        } else {
            //right
            if(mSetArrayList != null) {
                index = score - 200 + mSetArrayList.size() ;
            }

        }

        Log.d(LOG_TAG , "index num  " + index);
    }

    public void reset() {

        if(mSetArrayList != null && !mSetArrayList.isEmpty()) {
            mSetArrayList.clear();
        }

    }
}
