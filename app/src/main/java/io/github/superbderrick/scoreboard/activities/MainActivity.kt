package io.github.superbderrick.scoreboard.activities

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import io.github.superbderrick.scoreboard.R
import io.github.superbderrick.scoreboard.match.Game
import io.github.superbderrick.scoreboard.match.Match
import io.github.superbderrick.scoreboard.match.Rally
import io.github.superbderrick.scoreboard.match.Side
import io.github.superbderrick.scoreboard.theme.ThemeOperator
import io.github.superbderrick.scoreboard.ui.TouchLayout
import io.github.superbderrick.scoreboard.ui.Utils

/**
 * Table tennis scoreboard. All state lives in [Match]; this class only draws it and forwards taps.
 *
 * Upper half of each side: that player won the ball.
 * Centre button: "didn't see who won" - the ball is recorded as unknown, so the score and serve
 * order can go on, and the ball can be assigned later from the history dialog.
 * Lower half of each side / undo button: undo the last ball.
 */
class MainActivity : Activity() {
    private lateinit var leftScoreText: TextView
    private lateinit var rightScoreText: TextView
    private lateinit var leftGamesText: TextView
    private lateinit var rightGamesText: TextView
    private lateinit var leftName: EditText
    private lateinit var rightName: EditText
    private lateinit var leftServe: TextView
    private lateinit var rightServe: TextView
    private lateinit var statusText: TextView
    private lateinit var unknownButton: Button

    private lateinit var match: Match
    private var clickedSettingButton = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        bindViews()
        ThemeOperator(themeValue(), this).applyTheme()
        leftServe.setTextColor(leftScoreText.currentTextColor)
        rightServe.setTextColor(rightScoreText.currentTextColor)
        statusText.setTextColor(leftScoreText.currentTextColor)

        val saved = getSharedPreferences(PREFS_NAME, 0).getString(KEY_MATCH, null)
        match = (if (saved != null) Match.decode(saved) else null) ?: newMatch()
        refresh()
    }

    private fun bindViews() {
        leftScoreText = findViewById(R.id.leftScoreTextview)
        rightScoreText = findViewById(R.id.rightScoreTextview)
        leftGamesText = findViewById(R.id.leftSetScoreTextview)
        rightGamesText = findViewById(R.id.rightsetscoretextview)
        leftName = findViewById(R.id.leftUserName)
        rightName = findViewById(R.id.rightUserEdit)
        leftServe = findViewById(R.id.leftServeIndicator)
        rightServe = findViewById(R.id.rightServeIndicator)
        statusText = findViewById(R.id.statusText)
        unknownButton = findViewById(R.id.unknownButton)

        findViewById<TouchLayout>(R.id.leftUpperTouchView).setOnClickListener { addRally(Rally.LEFT) }
        findViewById<TouchLayout>(R.id.rightUpperTouchView).setOnClickListener { addRally(Rally.RIGHT) }
        findViewById<TouchLayout>(R.id.leftBottomTouchView).setOnClickListener { undo() }
        findViewById<TouchLayout>(R.id.rightBottomTouchView).setOnClickListener { undo() }
        unknownButton.setOnClickListener { addRally(Rally.UNKNOWN) }
        findViewById<Button>(R.id.undoButton).setOnClickListener { undo() }
        findViewById<Button>(R.id.historyButton).setOnClickListener { showHistory() }
        findViewById<ImageButton>(R.id.timerResetButton).setOnClickListener { confirmReset() }
        findViewById<ImageButton>(R.id.settingButton).setOnClickListener {
            Utils.showDialog(this, "Game Settings", getString(R.string.gamesetting_guide))
            clickedSettingButton = true
        }
    }

    private fun prefs() = PreferenceManager.getDefaultSharedPreferences(baseContext)

    private fun themeValue(): Int = prefs().getString("themekey", "1")!!.toInt()

    /** "5" / "3" / "1" games in the settings screen -> games needed to win the match. */
    private fun newMatch(): Match {
        val games = prefs().getString(getString(R.string.setscore_key), "5")!!.toInt()
        return Match(games / 2 + 1)
    }

    private fun name(side: Side): String {
        val text = (if (side == Side.LEFT) leftName else rightName).text.toString().trim()
        return if (text.isNotEmpty()) text else if (side == Side.LEFT) "左" else "右"
    }

    private fun addRally(rally: Rally) {
        if (!match.addRally(rally)) {
            // Game is over (or match is over): re-show the end-of-game dialog instead of ignoring the tap.
            checkGameEnd()
            return
        }
        refresh()
        checkGameEnd()
    }

    private fun undo() {
        match.undo()
        refresh()
    }

    private fun refresh() {
        val game = match.currentGame
        leftScoreText.text = game.leftScore.toString()
        rightScoreText.text = game.rightScore.toString()
        leftGamesText.text = match.leftGames.toString()
        rightGamesText.text = match.rightGames.toString()

        val serving = game.status != Game.Status.OVER
        leftServe.visibility = if (serving && game.server == Side.LEFT) View.VISIBLE else View.INVISIBLE
        rightServe.visibility = if (serving && game.server == Side.RIGHT) View.VISIBLE else View.INVISIBLE

        val unknown = game.unknownCount
        statusText.text = if (unknown > 0) getString(R.string.pending_count, unknown) else ""
        unknownButton.text = getString(R.string.unknown_ball)
    }

    private fun checkGameEnd() {
        val game = match.currentGame
        when {
            match.isOver -> AlertDialog.Builder(this)
                    .setTitle(R.string.match_over_title)
                    .setMessage("${name(match.winner!!)} 获胜  ${match.leftGames} : ${match.rightGames}")
                    .setPositiveButton(R.string.new_match) { _, _ ->
                        match = newMatch()
                        refresh()
                    }
                    .setNegativeButton(R.string.close, null)
                    .show()
            game.status == Game.Status.OVER -> AlertDialog.Builder(this)
                    .setTitle(getString(R.string.new_game_title, match.games.size))
                    .setMessage("${game.leftScore} : ${game.rightScore}")
                    .setPositiveButton(R.string.next_game) { _, _ ->
                        match.startNextGame()
                        refresh()
                    }
                    .setNegativeButton(R.string.close, null)
                    .show()
            game.status == Game.Status.MAYBE_OVER ->
                Toast.makeText(this, getString(R.string.resolve_pending, game.unknownCount), Toast.LENGTH_LONG).show()
            else -> {
            }
        }
    }

    /** Lists the balls of the current game; tapping an unknown ball lets you assign it. */
    private fun showHistory() {
        val game = match.currentGame
        if (game.rallies.isEmpty()) {
            Toast.makeText(this, R.string.history_empty, Toast.LENGTH_SHORT).show()
            return
        }
        val labels = game.rallies.mapIndexed { i, r ->
            val who = when (r) {
                Rally.LEFT -> name(Side.LEFT) + " 得分"
                Rally.RIGHT -> name(Side.RIGHT) + " 得分"
                Rally.UNKNOWN -> "? 没看清 (点击补录)"
            }
            "${i + 1}.  $who"
        }.toTypedArray<CharSequence>()
        AlertDialog.Builder(this)
                .setTitle("第 ${match.games.size} 局  ${game.leftScore} : ${game.rightScore}")
                .setItems(labels) { _, index ->
                    if (game.rallies[index] == Rally.UNKNOWN) assignBall(index)
                }
                .setNegativeButton(R.string.close, null)
                .show()
    }

    private fun assignBall(index: Int) {
        val game = match.currentGame
        val options = arrayOf<CharSequence>(name(Side.LEFT) + " 得分", name(Side.RIGHT) + " 得分")
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.assign_ball_title, index + 1))
                .setItems(options) { _, which ->
                    val side = if (which == 0) Side.LEFT else Side.RIGHT
                    if (game.resolve(index, side)) {
                        refresh()
                        checkGameEnd()
                    } else {
                        Toast.makeText(this, R.string.cannot_resolve, Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
    }

    private fun confirmReset() {
        AlertDialog.Builder(this)
                .setTitle(R.string.reset_title)
                .setMessage(R.string.reset_message)
                .setPositiveButton(R.string.ok) { _, _ ->
                    match = newMatch()
                    refresh()
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
    }

    override fun onResume() {
        super.onResume()
        if (clickedSettingButton) {
            // Game settings changed: start a fresh match with them.
            clickedSettingButton = false
            match = newMatch()
            ThemeOperator(themeValue(), this).applyTheme()
            refresh()
        }
    }

    override fun onStop() {
        super.onStop()
        getSharedPreferences(PREFS_NAME, 0).edit().putString(KEY_MATCH, match.encode()).apply()
    }

    companion object {
        const val PREFS_NAME = "MyPrefsFile"
        private const val KEY_MATCH = "match"
    }
}
