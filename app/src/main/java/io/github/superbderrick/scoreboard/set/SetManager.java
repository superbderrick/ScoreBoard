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
    private int mSetNum = 0;

    public SetManager() {

        mSetArrayList = new ArrayList<Set>();
    }


    public void setSetNum(int setNum) {
        mSetNum = setNum * 2;

        Log.d(LOG_TAG, "inputed size" + mSetNum);
        makeSetArray(mSetNum);
    }

    private void makeSetArray(int mSetNum) {
        for(int i = 0 ; i< mSetNum ; i++) {
            Set set = new Set();
            mSetArrayList.add(set);
        }
    }

    public void setScore(int score) {
        int index = 0;

        if(score < 200 ) {
             index = score - 100 ;
        } else {
            if(mSetArrayList != null) {
                index = score - 200 + mSetArrayList.size() ;
            }
        }
        Log.d(LOG_TAG , "Current num : " + mSetArrayList.size());
//
//        mSetArrayList.get(index).setTouched(true);
//
//        Log.d(LOG_TAG , "index num  " + index);
//
//        for(Set value :mSetArrayList) {
//            Log.d(LOG_TAG , "touched  " + value.isTouched());
//        }
    }


    public void reset() {
        if(mSetArrayList != null && !mSetArrayList.isEmpty()) {
            mSetArrayList.clear();
        }
    }
}
