package com.tixon.timemanagement.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tixon.timemanagement.R;
import com.tixon.timemanagement.fragments.FragmentAllTasks;
import com.tixon.timemanagement.fragments.FragmentEisenhowerMatrix;
import com.tixon.timemanagement.fragments.FragmentIncomesExpenses;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    FragmentManager fm;
    FragmentEisenhowerMatrix eisenhowerMatrix;
    FragmentAllTasks fragmentAllTasks;
    FragmentIncomesExpenses fragmentIncomes, fragmentExpenses, fragmentIncomesExpenses;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //navigationView.setCheckedItem(R.id.eisenhowerMatrix);

        fm = getFragmentManager();
        eisenhowerMatrix = FragmentEisenhowerMatrix.newInstance();
        fragmentAllTasks = FragmentAllTasks.newInstance();
        fragmentIncomes = FragmentIncomesExpenses.newInstance(FragmentIncomesExpenses.INCOMES);
        fragmentExpenses = FragmentIncomesExpenses.newInstance(FragmentIncomesExpenses.EXPENSES);
        fragmentIncomesExpenses = FragmentIncomesExpenses.newInstance(FragmentIncomesExpenses.INCOMES_AND_EXPENSES);
        //открытие главного фрагмента
        fm.beginTransaction().replace(R.id.navigationContainer, eisenhowerMatrix).commit();

        sPref = PreferenceManager.getDefaultSharedPreferences(NavigationActivity.this);
        sPref.registerOnSharedPreferenceChangeListener(NavigationActivity.this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eisenhowerMatrix:
                fm.beginTransaction().replace(R.id.navigationContainer, eisenhowerMatrix).commit();
                break;
            case R.id.allTasks:
                fm.beginTransaction().replace(R.id.navigationContainer, fragmentAllTasks).commit();
                break;
            case R.id.incomesExpenses:
                fm.beginTransaction().replace(R.id.navigationContainer, fragmentIncomesExpenses).commit();
                break;
            /*case R.id.incomes:
                fm.beginTransaction().replace(R.id.navigationContainer, fragmentIncomes).commit();
                break;
            case R.id.expenses:
                fm.beginTransaction().replace(R.id.navigationContainer, fragmentExpenses).commit();
                break;
            case R.id.incomesAndExpenses:
                fm.beginTransaction().replace(R.id.navigationContainer, fragmentIncomesExpenses)
                        .commit();
                break;*/
            case R.id.settings:
                startActivity(new Intent(NavigationActivity.this, SettingsActivity.class));
                break;
            default: break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
