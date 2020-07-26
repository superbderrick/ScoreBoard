package io.github.superbderrick.scoreboard.activities

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.net.Uri
import android.os.Bundle
import android.preference.*
import android.preference.Preference.OnPreferenceClickListener
import io.github.superbderrick.scoreboard.R
import io.github.superbderrick.scoreboard.ui.Utils

/**
 * Created by derricks on 27/01/2018.
 */
class SettingActivity : PreferenceActivity() {
    public override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, MyPreferenceFragment()).commit()
    }

    class MyPreferenceFragment : PreferenceFragment() {
        var mGameScoreReference: ListPreference? = null
        var mGameHandyPreference: ListPreference? = null
        var mGameThemePreference: ListPreference? = null
        var mEmailPreference: Preference? = null
        var mPrefs: SharedPreferences? = null
        var mKeywordScreen: PreferenceScreen? = null
        override fun onCreate(savedInstanceState: Bundle) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preference)
            mPrefs = PreferenceManager.getDefaultSharedPreferences(activity)
            mPrefs.registerOnSharedPreferenceChangeListener(prefListener)
            mGameHandyPreference = findPreference("handkey") as ListPreference
            mGameScoreReference = findPreference("setscorekey") as ListPreference
            mKeywordScreen = findPreference("keyword_screen") as PreferenceScreen
            mEmailPreference = findPreference("contactDevKey") as Preference
            mEmailPreference!!.onPreferenceClickListener = mPrefClickListener
            mGameThemePreference = findPreference("themekey") as ListPreference
            mGameThemePreference!!.summary = "Dark"
            val handSentence = Utils.getHandaySettingSentence(mGameHandyPreference!!.value)
            mGameHandyPreference!!.summary = handSentence
            val setSentence = Utils.getSetScoreSettingSentence(mGameScoreReference!!.value)
            mGameScoreReference!!.summary = setSentence
            val gameTheme = Utils.getGameThemeSentence(mGameThemePreference!!.value)
            mGameThemePreference!!.summary = gameTheme
        }

        var prefListener = OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "handkey") {
                val handSentence = Utils.getHandaySettingSentence(mGameHandyPreference!!.value)
                mGameHandyPreference!!.summary = handSentence
            } else if (key == "setscorekey") {
                val setSentence = Utils.getSetScoreSettingSentence(mGameScoreReference!!.value)
                mGameScoreReference!!.summary = setSentence
            } else if (key == "themekey") {
                val gameTheme = Utils.getGameThemeSentence(mGameThemePreference!!.value)
                mGameThemePreference!!.summary = gameTheme
            }
        }
        var mPrefClickListener = OnPreferenceClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto: kang.derrick@gmail.com")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hi Derrick")
            startActivity(Intent.createChooser(emailIntent, "Send feedback"))
            false
        }
    }
}