package com.tixon.timemanagement.fragments;

import android.os.Bundle;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.tixon.timemanagement.R;

/**
 * Created by tikhon on 05.03.16
 */
public class FragmentSettings extends PreferenceFragment {

    SwitchPreference switchNotifications;
    PreferenceCategory categoryNotifications;

    public static FragmentSettings newInstance() {
        FragmentSettings fragment = new FragmentSettings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        switchNotifications = (SwitchPreference)
                findPreference(getString(R.string.switchNotifications));
        categoryNotifications = (PreferenceCategory)
                findPreference(getString(R.string.categoryNotifications));


        //categoryNotifications.setEnabled(switchNotifications.isChecked());
        /*switchNotifications.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        return false;
                    }
                }
        );*/
    }
}
