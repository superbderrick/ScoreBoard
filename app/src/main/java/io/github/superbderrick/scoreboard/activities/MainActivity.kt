package io.github.superbderrick.scoreboard.activities

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import io.github.superbderrick.scoreboard.R
import io.github.superbderrick.scoreboard.activities.MainActivity
import io.github.superbderrick.scoreboard.helper.ScoreManager
import io.github.superbderrick.scoreboard.helper.ScoreManager.OnScoreChangeListener
import io.github.superbderrick.scoreboard.helper.ScoreManager.UserType
import io.github.superbderrick.scoreboard.set.SetManager
import io.github.superbderrick.scoreboard.set.SetManager.OnSetInfoListener
import io.github.superbderrick.scoreboard.settings.Handy
import io.github.superbderrick.scoreboard.settings.HandyCalculator
import io.github.superbderrick.scoreboard.theme.ThemeOperator
import io.github.superbderrick.scoreboard.ui.CircleView
import io.github.superbderrick.scoreboard.ui.CircleView.OnCircleViewChangeListener
import io.github.superbderrick.scoreboard.ui.TouchLayout
import io.github.superbderrick.scoreboard.ui.Utils
import java.util.*

class MainActivity : Activity() {
    private var mLeftUpperTouchView: TouchLayout? = null
    private var mLeftBottomTouchView: TouchLayout? = null
    private var mRightUpperTouchView: TouchLayout? = null
    private var mRightBottomTouchView: TouchLayout? = null
    private var mLeftScoreTextView: TextView? = null
    private var mRightScoreTextView: TextView? = null
    private var mLeftSetScoreTextview: TextView? = null
    private var mRightSetScoreTextview: TextView? = null
    private var mLeftUserName: EditText? = null
    private var mRightUserName: EditText? = null
    private var mSettingButton: ImageButton? = null
    private var mResetButton: ImageButton? = null
    private var mLeftScoreLayout: LinearLayout? = null
    private var mRightScoreLayout: LinearLayout? = null
    private val mMainHandler = Handler()
    private var mScoreManager: ScoreManager? = null
    private var mSetManager: SetManager? = null
    private var mThemeOperator: ThemeOperator? = null
    private var mClickedSettingButton = false
    private var mLeftSetScore = 0
    private var mRightSetScore = 0
    private val mTempCircleViewArrayList = ArrayList<CircleView>()
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        setGameListeners()
        initGUIComponent()
        bringSettingValues()
    }

    private fun setGameListeners() {
        mScoreManager = ScoreManager()
        //mScoreManager.setScoreMaxRange(ScoreManager.Companion.DEFAULT_MAXIMUM_SCORE)
        //mScoreManager.setListener(mScoreListener)
        mSetManager = SetManager()
        mSetManager!!.setListener(mSetInfoListener)
    }

    private fun applyGameTheme(themeValue: Int) {
        mThemeOperator = ThemeOperator(themeValue, this)
        mThemeOperator!!.applyTheme()
    }

    private fun bringSettingValues() {
        val SP = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val setCount = SP.getString(this.resources.getString(R.string.setscore_key), "5")
        val handyValue = SP.getString(this.resources.getString(R.string.handyy_key), "0")
        val themeValue = SP.getString("themekey", "1")
        val iThemeValue = themeValue.toInt()
        setupSettings(setCount, handyValue, iThemeValue)
    }

    private fun setupSettings(setCount: String, handyValue: String, themeValue: Int) {
        setSetModule(setCount, themeValue)
        setHandyPoint(handyValue)
        applyGameTheme(themeValue)
    }

    private fun setSetModule(setCount: String, themeValue: Int) {
        mMainHandler.post {
            val setNum = setCount.toInt()
            setupSetCircleView(setNum, themeValue)
            mSetManager!!.reset()
            mSetManager!!.setSetNum(setNum)
        }
    }

    private fun setHandyPoint(handyValue: String) {
        val handy = HandyCalculator.getHandy(handyValue.toInt())
        mMainHandler.post {
            if (handy.direction < Handy.Companion.RIGHT_USER) { // left
                mScoreManager!!.changeScore(ScoreManager.Operation.Increase, UserType.First, handy.handyPoint)
            } else { // right
                mScoreManager!!.changeScore(ScoreManager.Operation.Increase, UserType.Second, handy.handyPoint)
            }
        }
    }

    private fun initGUIComponent() {
        initLeftSideComponents()
        initRightSideComponents()
        initSettingButton()
        initResetButton()
        initScoreLayout()
        initSetScoreLayout()
    }

    private fun resetUsersName() {
        if (mLeftUserName != null && mRightUserName != null) {
            mLeftUserName!!.setText("")
            mRightUserName!!.setText("")
        }
    }

    private fun initSetScoreLayout() {
        mLeftSetScoreTextview = findViewById(R.id.leftSetScoreTextview)
        mRightSetScoreTextview = findViewById(R.id.rightsetscoretextview)
    }

    private fun initScoreLayout() {
        mLeftScoreLayout = findViewById(R.id.leftScoreLayout)
        mRightScoreLayout = findViewById(R.id.rightScoreLayout)
    }

    private fun setupSetCircleView(setNum: Int, themeValue: Int) {
        mMainHandler.post {
            if (mLeftScoreLayout != null) {
                makeCircleView(setNum, mLeftScoreLayout, DIRECTION_LEFT, themeValue)
                makeCircleView(setNum, mRightScoreLayout, DIRECTION_RIGHT, themeValue)
            }
        }
    }

    private fun makeCircleView(setNum: Int, layout: LinearLayout?, direction: Int, themeValue: Int) {
        layout!!.removeAllViews()
        for (i in 0 until setNum) {
            val circleView = CircleView(this@MainActivity)
            circleView.setCircleColor(themeValue)
            circleView.setNormalColor(themeValue)
            circleView.id = i + direction
//            circleView.listener = mCircleViewListener
//            val lp = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    0, 1)
//            circleView.layoutParams = lp
//            layout.addView(circleView)
//            mTempCircleViewArrayList.add(circleView)
        }
    }

    private fun initResetButton() {
        mResetButton = findViewById(R.id.timerResetButton)
//        mResetButton.setOnClickListener(View.OnClickListener {
//            val alertbox = AlertDialog.Builder(this@MainActivity)
//            alertbox.setTitle("Reset current game")
//            alertbox.setCancelable(false)
//            alertbox.setMessage("Do you want to reset all the game score?")
//            alertbox.setPositiveButton("OK") { dialog, which ->
//                resetValues()
//                mSetManager!!.resetTouchValue()
//                for (i in mTempCircleViewArrayList.indices) {
//                    mTempCircleViewArrayList[i].resetCircleViewColor()
//                }
//            }
//            alertbox.setNegativeButton("CANCEL") { dialog, which -> dialog.dismiss() }
//            alertbox.show()
//        })
    }

    private fun initSettingButton() {
        mSettingButton = findViewById(R.id.settingButton)
//        mSettingButton.bringToFront()
//        mSettingButton.setOnClickListener(View.OnClickListener {
//            Utils.showDialog(this@MainActivity, "Game Settings", getString(R.string.gamesetting_guide))
//            mClickedSettingButton = true
//            mTempCircleViewArrayList.clear()
//        })
    }

    private fun initRightSideComponents() {
        mRightUpperTouchView = findViewById(R.id.rightUpperTouchView)
        mRightBottomTouchView = findViewById(R.id.rightBottomTouchView)
        mRightScoreTextView = findViewById(R.id.rightScoreTextview)
//        mRightUpperTouchView.setOnClickListener(View.OnClickListener { mScoreManager!!.changeScore(ScoreManager.Operation.Increase, UserType.Second) })
//        mRightBottomTouchView.setOnClickListener(View.OnClickListener { mScoreManager!!.changeScore(ScoreManager.Operation.Decrease, UserType.Second) })
    }

    private fun initLeftSideComponents() {
        mLeftUpperTouchView = findViewById(R.id.leftUpperTouchView)
        mLeftBottomTouchView = findViewById(R.id.leftBottomTouchView)
        mLeftScoreTextView = findViewById(R.id.leftScoreTextview)
        mLeftUserName = findViewById(R.id.leftUserName)
        mRightUserName = findViewById(R.id.rightUserEdit)
//        mLeftUpperTouchView.setOnClickListener(View.OnClickListener { mScoreManager!!.changeScore(ScoreManager.Operation.Increase, UserType.First) })
//        mLeftBottomTouchView.setOnClickListener(View.OnClickListener { mScoreManager!!.changeScore(ScoreManager.Operation.Decrease, UserType.First) })
    }

    var mScoreListener: OnScoreChangeListener = object : OnScoreChangeListener {
        override fun onFirstScoreChanged(score: Int) {
            mMainHandler.post {
                val finalScore = "" + score
                mLeftScoreTextView!!.text = finalScore
            }
        }

        override fun onSecondScoreChanged(score: Int) {
            mMainHandler.post {
                val finalScore = "" + score
                mRightScoreTextView!!.text = finalScore
            }
        }

        override fun onScoreInitialized(score: Int) {
            mMainHandler.post {
                val finalScore = "" + score
                mRightScoreTextView!!.text = finalScore
                mLeftScoreTextView!!.text = finalScore
            }
        }
    }
//    var mCircleViewListener = OnCircleViewChangeListener { id ->
//        if (mSetManager != null) {
//            mSetManager!!.setScore(id)
//        }
//    }
    var mSetInfoListener: OnSetInfoListener = object : OnSetInfoListener {
        override fun onSetInfo(scores: IntArray) {
            setSetScoreLayout(scores[0], scores[1])
        }

        override fun onSetInitialized() {
            setSetScoreLayout(0, 0)
        }
    }

    private fun setSetScoreLayout(left: Int, right: Int) {
        mMainHandler.post {
            mLeftSetScoreTextview!!.text = "" + left
            mRightSetScoreTextview!!.text = "" + right
            mLeftSetScore = left
            mRightSetScore = right
        }
    }

    private fun resetValues() {
        mMainHandler.post {
            if (mScoreManager != null && mSetManager != null) {
                mScoreManager!!.resetScore()
            }
            resetUsersName()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        saveSetScoreData()
    }

    private fun saveSetScoreData() {
        val settings = getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putInt("leftSetScore", mLeftSetScore)
        editor.putInt("rightSetScore", mRightSetScore)
        editor.commit()
    }

    override fun onPause() {
        super.onPause()
        if (mClickedSettingButton) {
            mMainHandler.post { resetValues() }
        }
    }

    override fun onResume() {
        super.onResume()
        if (mClickedSettingButton) {
            mClickedSettingButton = false
            bringSettingValues()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val LOG_TAG = "MainActivity"
        const val PREFS_NAME = "MyPrefsFile"
        private const val DIRECTION_LEFT = 100
        private const val DIRECTION_RIGHT = 200
    }
}