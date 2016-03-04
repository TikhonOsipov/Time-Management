package com.tixon.timemanagement.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.j256.ormlite.stmt.UpdateBuilder;
import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.Util;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.ActivityTaskDescriptionBinding;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;
import java.util.Calendar;
import android.os.Handler;

/**
 * Created by tikhon on 03.03.16
 */
public class TaskDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityTaskDescriptionBinding binding;
    Animation rotateFab;
    Animation rotateFab180, rotateFab360;
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

        rotateFab = AnimationUtils.loadAnimation(this, R.anim.rotate_fab);

        id = fromTaskList.getIntExtra(Constants.EXTRA_ID, 0);
        task = getTask(id);
        if(task != null) {
            binding.setTask(task);
            binding.tdDescription.setVisibility(task.getDescription().isEmpty() ?
                    View.GONE : View.VISIBLE);
            binding.taskDescriptionDateTimeLayout.setVisibility(task.isUrgent() ?
                    View.VISIBLE : View.GONE);
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
        binding.tdDate.setOnClickListener(this);
        binding.tdTime.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isInEditMode) {
            binding.tdTitle.clearFocus();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tdDate:
                showDatePickerDialog();
                break;
            case R.id.tdTime:
                showTimePickerDialog();
                break;
            default:
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                binding.tdDate.setText(getString(R.string.dateFormat, dayOfMonth, monthOfYear+1, year));
            }
        };

        if(!binding.tdDate.getText().toString().isEmpty()) {
            int[] date = Util.transformStringToDate(binding.tdDate.getText().toString());
            datePickerDialog = new DatePickerDialog(TaskDescriptionActivity.this,
                    dateSetListener, date[2], date[1]-1, date[0]);
        } else {
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(TaskDescriptionActivity.this,
                    dateSetListener, year, month, day);
        }
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                binding.tdTime.setText(getString(R.string.timeFormat, hourOfDay, minute));
            }
        };

        if(!binding.tdTime.getText().toString().isEmpty()) {
            int[] time = Util.transformStringToTime(binding.tdTime.getText().toString());
            timePickerDialog = new TimePickerDialog(TaskDescriptionActivity.this,
                    timeSetListener, time[0], time[1], true);
        } else {
            int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            timePickerDialog = new TimePickerDialog(TaskDescriptionActivity.this,
                    timeSetListener, hourOfDay, minute, true);
        }
        timePickerDialog.show();
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
        binding.fabDescription.startAnimation(rotateFab);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.fabDescription.setImageResource(R.drawable.ic_save_white_24dp);

                binding.tdTitle.setEnabled(true);
                binding.tdTitle.setFocusableInTouchMode(true);
                binding.tdDescription.setEnabled(true);
                binding.tdDescription.setFocusableInTouchMode(true);
                binding.tdDate.setEnabled(true);
                binding.tdTime.setEnabled(true);

                binding.tdDescription.setVisibility(View.VISIBLE);
                binding.taskDescriptionDateTimeLayout.setVisibility(View.VISIBLE);

                binding.tdTitle.requestFocus();
                binding.tdTitle.setSelection(binding.tdTitle.getText().length());

                isInEditMode = true;
            }
        }, 250);
    }

    /**
     * Выйти из режима редактирования
     */
    private void quitEditMode() {
        if(binding.tdTitle.getText().toString().isEmpty()) {
            binding.tdTitle.setError(getString(R.string.titleEmptyError));
            binding.tdTitle.requestFocus();
        } else {
            binding.fabDescription.startAnimation(rotateFab);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.fabDescription.setImageResource(R.drawable.ic_edit_white_24dp);

                    binding.tdTitle.setEnabled(false);
                    binding.tdTitle.setFocusableInTouchMode(false);
                    binding.tdDescription.setEnabled(false);
                    binding.tdDescription.setFocusableInTouchMode(false);
                    binding.tdDate.setEnabled(false);
                    binding.tdTime.setEnabled(false);

                    binding.tdDescription.setVisibility(
                            binding.tdDescription.getText().toString().isEmpty() ?
                                    View.GONE : View.VISIBLE);
                    binding.taskDescriptionDateTimeLayout.setVisibility(
                            binding.tdDate.getText().toString().isEmpty() &&
                                    binding.tdTime.getText().toString().isEmpty() ?
                                    View.GONE : View.VISIBLE);

                    isInEditMode = false;
                    binding.taskDescriptionLayout.clearFocus();
                }
            }, 250);
        }
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
        boolean urgent = !(binding.tdDate.getText().toString().isEmpty() &&
                binding.tdTime.getText().toString().isEmpty());
        try {
            UpdateBuilder<Task, Integer> updateBuilder = taskDAO.updateBuilder();
            updateBuilder.where().eq("id", id);
            updateBuilder.updateColumnValue("description", binding.tdDescription.getText().toString());
            updateBuilder.updateColumnValue("title", binding.tdTitle.getText().toString());
            updateBuilder.updateColumnValue("time", binding.tdTime.getText().toString());
            updateBuilder.updateColumnValue("date", binding.tdDate.getText().toString());
            updateBuilder.updateColumnValue("urgent", urgent);
            updateBuilder.update();
        } catch (SQLException e) {
            Log.e("myLogs", "error updating task in TaskDescriptionActivity: " + e.toString());
            e.printStackTrace();
        }
    }
}
