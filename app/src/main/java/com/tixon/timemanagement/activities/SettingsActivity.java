package com.tixon.timemanagement.activities;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tixon.timemanagement.R;
import com.tixon.timemanagement.databinding.ActivitySettingsBinding;
import com.tixon.timemanagement.fragments.FragmentSettings;

/**
 * Created by tikhon on 05.03.16
 */
public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        setSupportActionBar(binding.settingsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.settingsContainer, FragmentSettings.newInstance())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
