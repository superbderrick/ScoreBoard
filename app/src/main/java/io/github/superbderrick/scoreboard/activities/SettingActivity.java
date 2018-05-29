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
        ListPreference mGameTimePreference;
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

            //@string/gametime_key"
            mGameTimePreference = (ListPreference)findPreference("gameTimekey");
            mGameHandyPreference = (ListPreference)findPreference("handkey");
            mGameScorereference = (ListPreference)findPreference("setscorekey");
            mKeywordScreen = (PreferenceScreen)findPreference("keyword_screen");


            mPrefs.registerOnSharedPreferenceChangeListener(prefListener);
        }
        SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                Log.d("derrick" , "s: " + s);
            }
        };
    }
}
