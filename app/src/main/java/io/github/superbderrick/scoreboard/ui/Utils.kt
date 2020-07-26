package io.github.superbderrick.scoreboard.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import io.github.superbderrick.scoreboard.activities.SettingActivity

/**
 * Created by derrick on 25/05/2018.
 */
object Utils {
    fun showDialog(activity: Activity, title: String?, message: String?) {
        val alertbox = AlertDialog.Builder(activity)
        alertbox.setTitle(title)
        alertbox.setCancelable(false)
        alertbox.setMessage(message)
        alertbox.setPositiveButton("OK") { dialog, which -> moveSettingScreen(activity) }
        alertbox.setNegativeButton("CANCEL") { dialog, which -> dialog.dismiss() }
        alertbox.show()
    }

    private fun moveSettingScreen(activity: Activity) {
        val intent = Intent(activity, SettingActivity::class.java)
        activity.startActivity(intent)
    }

    fun getHandaySettingSentence(prefSentence: String): String {
        val handyValue = prefSentence.toInt()
        var handSentence = "same"
        handSentence = if (handyValue == 0) {
            "No Handy - Same Score"
        } else if (handyValue > 6) {
            "Right User " + " + " + (handyValue - 6)
        } else {
            "Left User + $handyValue"
        }
        return handSentence
    }

    fun getSetScoreSettingSentence(prefSentence: String): String {
        val setValue = prefSentence.toInt()
        return "Game Set : $setValue"
    }

    fun getGameThemeSentence(prefSentence: String): String {
        val setValue = prefSentence.toInt()
        var theme = "Dark"
        theme = if (setValue == 0) {
            "Dark"
        } else {
            "Light"
        }
        return "Game Theme  $theme"
    }
}