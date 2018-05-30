package io.github.superbderrick.scoreboard.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

import io.github.superbderrick.scoreboard.R;

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
        ListPreference mGameScorereference;
        ListPreference mGameHandyPreference;
        SharedPreferences mPrefs;
        PreferenceScreen mKeywordScreen;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
            mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            mGameHandyPreference = (ListPreference)findPreference("handkey");
            String handyString = "Handy Setting : " + mGameHandyPreference.getValue();

            mGameHandyPreference.setSummary(handyString);

            mGameScorereference = (ListPreference)findPreference("setscorekey");

            String setString = "Game Set : " + mGameScorereference.getValue();
            mGameScorereference.setSummary(setString);
            mKeywordScreen = (PreferenceScreen)findPreference("keyword_screen");


            mPrefs.registerOnSharedPreferenceChangeListener(prefListener);
        }
        SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.d("derrick" , "s: " + key);
                if(key.equals("handkey")) {
                    Log.d("derrick" , "value: " + mGameHandyPreference.getValue());

                    String handyString = "Handy Setting : " + mGameHandyPreference.getValue();

                    mGameHandyPreference.setSummary(handyString);

                } else if(key.equals("setscorekey")) {
                    Log.d("derrick" , "value: " + mGameScorereference.getValue());

                    String setString = "Game Set : " + mGameScorereference.getValue();
                    mGameScorereference.setSummary(setString);
                }
            }
        };
    }
}
