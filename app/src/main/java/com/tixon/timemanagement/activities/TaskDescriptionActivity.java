package com.tixon.timemanagement.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.j256.ormlite.stmt.UpdateBuilder;
import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.ActivityTaskDescriptionBinding;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;

/**
 * Created by tikhon on 03.03.16.
 */
public class TaskDescriptionActivity extends AppCompatActivity {

    ActivityTaskDescriptionBinding binding;
    Intent fromTaskList;
    TaskDAO taskDAO;
    Task task = null;
    boolean isInEditMode;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_description);
        setSupportActionBar(binding.toolbarTaskDescription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        fromTaskList = getIntent();

        id = fromTaskList.getIntExtra(Constants.EXTRA_ID, 0);
        task = getTask(id);
        if(task != null) {
            binding.setTask(task);
        } else {
            finish();
        }

        isInEditMode = false;

        binding.fabDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isInEditMode) {
                    enterEditMode();
                } else {
                    quitEditMode();
                    updateTask(id);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isInEditMode) {
            binding.tdTitle.clearFocus();
        }
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

    @Override
    public void onBackPressed() {
        //если в режиме редактирования
        if(isInEditMode) {
            quitEditMode(); //выйти из него
            binding.setTask(getTask(id));
        } else {
            super.onBackPressed(); //иначе закрыть activity
        }
    }

    /**
     * Перейти в режим редактирования
     */
    private void enterEditMode() {
        binding.tdTitle.setEnabled(true);
        binding.tdTitle.setFocusableInTouchMode(true);
        binding.tdDescription.setEnabled(true);
        binding.tdDescription.setFocusableInTouchMode(true);
        binding.tdTitle.requestFocus();
        binding.tdTitle.setSelection(binding.tdTitle.getText().length());
        binding.fabDescription.setImageResource(R.drawable.ic_save_white_24dp);
        isInEditMode = true;
    }

    /**
     * Выйти из режима редактирования
     */
    private void quitEditMode() {
        binding.tdTitle.setEnabled(false);
        binding.tdTitle.setFocusableInTouchMode(false);
        binding.tdDescription.setEnabled(false);
        binding.tdDescription.setFocusableInTouchMode(false);
        binding.fabDescription.setImageResource(R.drawable.ic_edit_white_24dp);
        isInEditMode = false;
    }

    /**
     * Получить задачу из БД по id
     * @param id: получить из Intent
     * @return задачу
     */
    private Task getTask(int id) {
        Task mTask = null;
        try {
            taskDAO = HelperFactory.getDatabaseHelper().getTaskDAO();
            mTask = taskDAO.queryForId(id);
        } catch (SQLException e) {
            Log.e("myLogs", "error in SQL in TaskDescriptionActivity: " + e.toString());
            e.printStackTrace();
        }
        return mTask;
    }

    /**
     * Обновить полученную задачу
     */
    private void updateTask(int id) {
        try {
            UpdateBuilder<Task, Integer> updateBuilder = taskDAO.updateBuilder();
            updateBuilder.where().eq("id", id);
            updateBuilder.updateColumnValue("description", binding.tdDescription.getText().toString());
            updateBuilder.updateColumnValue("title", binding.tdTitle.getText().toString());
            updateBuilder.update();
        } catch (SQLException e) {
            Log.e("myLogs", "error updating task in TaskDescriptionActivity: " + e.toString());
            e.printStackTrace();
        }
    }
}
