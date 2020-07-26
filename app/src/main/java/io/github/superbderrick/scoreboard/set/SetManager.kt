package io.github.superbderrick.scoreboard.set

import android.util.Log
import java.util.*

/**
 * Created by derrick on 22/05/2018.
 */
class SetManager {
    interface OnSetInfoListener {
        fun onSetInfo(scoreArray: IntArray)
        fun onSetInitialized()
    }

    private var mListener: OnSetInfoListener? = null
    fun setListener(mSetListener: OnSetInfoListener?) {
        mListener = mSetListener
    }

    private val mSetArrayList: ArrayList<Set>?
    private val mFinalScoreList: IntArray
    private var mLeftScore = 0
    private var mRightScore = 0
    private var mSetNum = 0
    fun setSetNum(setNum: Int) {
        mSetNum = setNum * 2
        makeSetArray(mSetNum)
    }

    private fun makeSetArray(mSetNum: Int) {
        for (i in 0 until mSetNum) {
            val set = Set()
            set.index = i
            set.isTouched = false
            mSetArrayList!!.add(set)
        }
    }

    fun resetTouchValue() {
        if (mSetArrayList != null) {
            for (i in mSetArrayList.indices) {
                mSetArrayList[i].isTouched = false
            }
            mLeftScore = 0
            mRightScore = 0
            mFinalScoreList[0] = 0
            mFinalScoreList[1] = 0
            mListener!!.onSetInfo(mFinalScoreList)
        }
    }

    fun setScore(score: Int) {
        arrangeTouchEvent(score)
        val finalArrayList = calculateSetScore()
        mLeftScore = 0
        mRightScore = 0
        mListener!!.onSetInfo(finalArrayList)
    }

    private fun calculateSetScore(): IntArray {
        for (i in mSetArrayList!!.indices) {
            if (i < mSetArrayList.size / 2) {
                if (mSetArrayList[i].isTouched == true) {
                    mLeftScore++
                }
            } else {
                if (mSetArrayList[i].isTouched == true) {
                    mRightScore++
                }
            }
        }
        mFinalScoreList[0] = mLeftScore
        mFinalScoreList[1] = mRightScore
        return mFinalScoreList
    }

    private fun arrangeTouchEvent(score: Int) {
        var index = 0
        if (score < 200) {
            index = score - 100
        } else {
            if (mSetArrayList != null) {
                index = score - 200
                index = if (index == 0) {
                    mSetArrayList.size / 2
                } else {
                    index + mSetArrayList.size / 2
                }
            }
        }
        if (mSetArrayList!![index].isTouched == false) {
            mSetArrayList[index].isTouched = true
        } else {
            mSetArrayList[index].isTouched = false
        }
    }

    fun reset() {
        Log.d(LOG_TAG, "reset is called ")
        if (mSetArrayList != null && !mSetArrayList.isEmpty()) {
            mSetArrayList.clear()
        }
        mLeftScore = 0
        mRightScore = 0
        mListener!!.onSetInitialized()
    }

    companion object {
        private const val LOG_TAG = "SetManager"
    }

    init {
        mSetArrayList = ArrayList()
        mFinalScoreList = IntArray(2)
    }
}