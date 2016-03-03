package com.tixon.timemanagement;

import android.app.FragmentManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tixon.timemanagement.activities.CreateTaskActivity;
import com.tixon.timemanagement.databinding.ActivityMainBinding;
import com.tixon.timemanagement.fragments.FragmentTasksList;
import com.tixon.timemanagement.task.Task;

import java.util.ArrayList;

public class MainActivityDelete extends AppCompatActivity {
    public static final int REQUEST_CODE_CREATE_TASK = 1;

    ActivityMainBinding binding;

    FragmentManager fm;
    FragmentTasksList fragmentUrgentAndImportant,
            fragmentUrgentAndNotImportant,
            fragmentNotUrgentAndImportant,
            fragmentNotUrgentAndNotImportant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivityDelete.this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        fm = getFragmentManager();

        fragmentUrgentAndImportant = FragmentTasksList.newInstance(true, true, getString(R.string.urgentAndImportant));
        fragmentUrgentAndNotImportant = FragmentTasksList.newInstance(true, false, getString(R.string.urgentAndNotImportant));
        fragmentNotUrgentAndImportant = FragmentTasksList.newInstance(false, true, getString(R.string.notUrgentAndImportant));
        fragmentNotUrgentAndNotImportant = FragmentTasksList.newInstance(false, false, getString(R.string.notUrgentAndNotImportant));

        fm.beginTransaction().replace(R.id.urgentAndImportant, fragmentUrgentAndImportant).commit();
        fm.beginTransaction().replace(R.id.urgentAndNotImportant, fragmentUrgentAndNotImportant).commit();
        fm.beginTransaction().replace(R.id.notUrgentAndImportant, fragmentNotUrgentAndImportant).commit();
        fm.beginTransaction().replace(R.id.notUrgentAndNotImportant, fragmentNotUrgentAndNotImportant).commit();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent createTask = new Intent(MainActivityDelete.this, CreateTaskActivity.class);
                startActivityForResult(createTask, REQUEST_CODE_CREATE_TASK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CREATE_TASK:
                if(resultCode == RESULT_OK) {
                    boolean urgent = data.getBooleanExtra(Constants.RESULT_EXTRA_URGENT, false);
                    boolean important = data.getBooleanExtra(Constants.RESULT_EXTRA_IMPORTANT, false);
                    showAnimation(urgent, important);
                }
                break;
            default: break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("unused")
    private void printTasks(ArrayList<Task> tasks) {
        for(Task task: tasks) {
            Log.d("myLogs", "id = " + task.getId() + ", title: " + task.getTitle() +
                    ", description: " + task.getDescription() +
                    ", important: " + task.isImportant() +
                    ", urgent = " + task.isUrgent());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showAnimation(boolean urgent, boolean important) {
        if(urgent && important) {
            fragmentUrgentAndImportant.showTaskAdded();
        }
        if(urgent && !important) {
            fragmentUrgentAndNotImportant.showTaskAdded();
        }
        if(!urgent && important) {
            fragmentNotUrgentAndImportant.showTaskAdded();
        }
        if(!urgent && !important) {
            fragmentNotUrgentAndNotImportant.showTaskAdded();
        }
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.urgentAndImportant:
                taskListActivityIntent.putExtra(Constants.EXTRA_URGENT, true);
                taskListActivityIntent.putExtra(Constants.EXTRA_IMPORTANT, true);
                taskListActivityIntent.putExtra(Constants.EXTRA_BACKGROUND,
                        ((ColorDrawable) findViewById(v.getId()).getBackground()).getColor());
                startActivity(taskListActivityIntent);
                break;
            case R.id.urgentAndNotImportant:
                taskListActivityIntent.putExtra(Constants.EXTRA_URGENT, true);
                taskListActivityIntent.putExtra(Constants.EXTRA_IMPORTANT, false);
                taskListActivityIntent.putExtra(Constants.EXTRA_BACKGROUND,
                        ((ColorDrawable) findViewById(v.getId()).getBackground()).getColor());
                startActivity(taskListActivityIntent);
                break;
            case R.id.notUrgentAndImportant:
                taskListActivityIntent.putExtra(Constants.EXTRA_URGENT, false);
                taskListActivityIntent.putExtra(Constants.EXTRA_IMPORTANT, true);
                taskListActivityIntent.putExtra(Constants.EXTRA_BACKGROUND,
                        ((ColorDrawable) findViewById(v.getId()).getBackground()).getColor());
                startActivity(taskListActivityIntent);
                break;
            case R.id.notUrgentAndNotImportant:
                taskListActivityIntent.putExtra(Constants.EXTRA_URGENT, false);
                taskListActivityIntent.putExtra(Constants.EXTRA_IMPORTANT, false);
                taskListActivityIntent.putExtra(Constants.EXTRA_BACKGROUND,
                        ((ColorDrawable) findViewById(v.getId()).getBackground()).getColor());
                startActivity(taskListActivityIntent);
                break;
        }
    }*/

    /*@Override
    public void onFragmentRecyclerClick(boolean urgent, boolean important, int color) {
        taskListActivityIntent.putExtra(Constants.EXTRA_URGENT, true);
        taskListActivityIntent.putExtra(Constants.EXTRA_IMPORTANT, true);
        taskListActivityIntent.putExtra(Constants.EXTRA_BACKGROUND, color);
        startActivity(taskListActivityIntent);
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
