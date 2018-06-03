package io.github.superbderrick.scoreboard.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

import io.github.superbderrick.scoreboard.R;
import io.github.superbderrick.scoreboard.ui.Utils;

/**
 * Created by derricks on 27/01/2018.
 */

public class SettingActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        ListPreference mGameScoreReference;
        ListPreference mGameHandyPreference;
        Preference mEmailPreference;
        SharedPreferences mPrefs;
        PreferenceScreen mKeywordScreen;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
            mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mPrefs.registerOnSharedPreferenceChangeListener(prefListener);

            mGameHandyPreference = (ListPreference)findPreference("handkey");
            mGameScoreReference = (ListPreference)findPreference("setscorekey");
            mKeywordScreen = (PreferenceScreen)findPreference("keyword_screen");
            mEmailPreference = (Preference)findPreference("contactDevKey");
            mEmailPreference.setOnPreferenceClickListener(mPrefClickListener);

            String handSentence = Utils.getHandaySettingSentence(mGameHandyPreference.getValue());
            mGameHandyPreference.setSummary(handSentence);
            String setSentence = Utils.getSetScoreSettingSentence(mGameScoreReference.getValue());
            mGameScoreReference.setSummary(setSentence);

        }
        SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("handkey")) {
                    String handSentence = Utils.getHandaySettingSentence(mGameHandyPreference.getValue());
                    mGameHandyPreference.setSummary(handSentence);

                } else if(key.equals("setscorekey")) {
                    String setSentence = Utils.getSetScoreSettingSentence(mGameScoreReference.getValue());
                    mGameScoreReference.setSummary(setSentence);
                }
            }
        };

        Preference.OnPreferenceClickListener mPrefClickListener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: kang.derrick@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hi Derrick");
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));

                return false;
            }
        };
    }
}
