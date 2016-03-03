package com.tixon.timemanagement.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.tixon.timemanagement.R;
import com.tixon.timemanagement.adapters.TaskListActivityAdapter;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.TaskListActivityBinding;
import com.tixon.timemanagement.fragments.FragmentTasksList;
import com.tixon.timemanagement.listeners.OnMultiSelectionListener;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tikhon on 28.02.16.
 */
public class TaskListActivity extends AppCompatActivity {

    TaskListActivityBinding binding;
    TaskDAO taskDAO;
    private boolean isInMultiSelection;
    private TaskListActivityAdapter adapter;
    private TransitionDrawable transition, reverseTransition;
    private String cachedToolbarTitle;
    private boolean urgent, important;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TaskListActivity.this,
                R.layout.task_list_activity);
        setSupportActionBar(binding.toolbarTaskList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Intent fromMain = getIntent();
        Bundle args = fromMain.getBundleExtra(FragmentTasksList.ARG_BUNDLE);
        urgent = args.getBoolean(FragmentTasksList.ARG_URGENT, false);
        important = args.getBoolean(FragmentTasksList.ARG_IMPORTANT, false);
        binding.toolbarTaskList.setTitle(args.getString(FragmentTasksList.ARG_NAME));
        cachedToolbarTitle = args.getString(FragmentTasksList.ARG_NAME);

        ColorDrawable indigo500 = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        ColorDrawable red800 = new ColorDrawable(getResources().getColor(R.color.red800));

        transition = new TransitionDrawable(new Drawable[] {indigo500, red800});
        reverseTransition = new TransitionDrawable(new Drawable[] {red800, indigo500});
        binding.toolbarTaskList.setBackground(transition);

        isInMultiSelection = false;

        try {
            taskDAO = HelperFactory.getDatabaseHelper().getTaskDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_items:
                Log.d("myLogs", "deleted");
                if(isInMultiSelection) {
                    adapter.deleteSelectedItems();
                    hideMultiSelectionMenu();
                }
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        adapter = new TaskListActivityAdapter(TaskListActivity.this, taskDAO, urgent, important);
        binding.taskListRecyclerView.setLayoutManager(
                new LinearLayoutManager(TaskListActivity.this));
        binding.taskListRecyclerView.setAdapter(adapter);

        adapter.setOnMultiSelectionListener(new OnMultiSelectionListener() {
            @Override
            public void onSelectionStart() {
                Log.d("myLogs", "onSelectionStart");
                showMultiSelectionMenu();
            }

            @Override
            public void onSelectionEnd(ArrayList<Task> taskList) {
                Log.d("myLogs", "onSelectionEnd");
                Log.d("myLogs", "cachedToolbarTitle = " + cachedToolbarTitle);
                binding.toolbarTaskList.setTitle(cachedToolbarTitle);
                hideMultiSelectionMenu();
                if (taskList.isEmpty()) {
                    finish();
                }
            }

            @Override
            public void onSelection(int count) {
                binding.toolbarTaskList.setTitle(getString(R.string.selected_number, count));
            }
        });
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(isInMultiSelection) {
            adapter.unCheckAll();
            hideMultiSelectionMenu();
        } else {
            super.onBackPressed();
        }
    }

    private void hideMultiSelectionMenu() {
        isInMultiSelection = false;
        binding.toolbarTaskList.setBackground(reverseTransition);
        binding.toolbarTaskList.setTitle(cachedToolbarTitle);
        reverseTransition.startTransition(500);
        //hide menu
        binding.toolbarTaskList.getMenu().clear();
    }

    private void showMultiSelectionMenu() {
        isInMultiSelection = true;
        binding.toolbarTaskList.setBackground(transition);
        binding.toolbarTaskList.setTitle(getString(R.string.selected_number, 1));
        transition.startTransition(500);
        //show menu
        getMenuInflater().inflate(R.menu.menu_delete_items, binding.toolbarTaskList.getMenu());
    }
}
