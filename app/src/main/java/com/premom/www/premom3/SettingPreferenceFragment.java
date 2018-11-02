package com.premom.www.premom3;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

public class SettingPreferenceFragment extends PreferenceFragment{
    SharedPreferences prefs;

    ListPreference themePreference;


    public void OnCreate(@Nullable Bundle savedInterState){
        super.onCreate(savedInterState);

        addPreferencesFromResource(R.xml.setting_preference);
        themePreference = (ListPreference)findPreference("theme_list");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("theme_list","").equals("")){
            themePreference.setSummary(prefs.getString("theme_list","핫핑크"));
        }

        prefs.registerOnSharedPreferenceChangeListener(preListener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener preListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("theme_list")){
                themePreference.setSummary(prefs.getString("theme_list","핫핑크"));
                }
        }
    };

}
