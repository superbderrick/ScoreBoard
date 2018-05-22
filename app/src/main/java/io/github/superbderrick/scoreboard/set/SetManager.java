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

    }

    public int getSetNum() {
        return mSetNum;
    }

    public void setSetNum(int mSetNum) {
        this.mSetNum = mSetNum;
    }

    public void setScore(int score) {

        Log.d(LOG_TAG , "score " + score);
    }

    public void reset() {

    }
}
