package com.tixon.timemanagement.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.databinding.ActivityCreateTaskBinding;
import com.tixon.timemanagement.receivers.TaskReceiver;
import com.tixon.timemanagement.task.Task;
import com.tixon.timemanagement.utils.NotificationUtils;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by tikhon on 26.02.16
 */
public class CreateTaskActivity extends AppCompatActivity {
    private ActivityCreateTaskBinding binding;
    private Calendar calendar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_task);
        setSupportActionBar(binding.toolbarCreateTask);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        readData();
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CreateTaskActivity.this);

        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLogs", "date clicked");
                DatePickerDialog datePickerDialog;
                String dateFromEt = binding.editTextDate.getText().toString();
                if(!dateFromEt.isEmpty()) {
                    int[] date = getDate(dateFromEt);
                    datePickerDialog = new DatePickerDialog(CreateTaskActivity.this,
                            dateSetListener, date[2], date[1]-1, date[0]);
                } else {
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    Log.d("myLogs", "day = " + day + ", month = " + month + ", year = " + year);

                    datePickerDialog = new DatePickerDialog(CreateTaskActivity.this,
                            dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                }
                datePickerDialog.show();
            }
        });

        binding.editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLogs", "time clicked");
                calendar.setTimeInMillis(System.currentTimeMillis());
                TimePickerDialog timePickerDialog;
                String timeFromEt = binding.editTextTime.getText().toString();
                if (!timeFromEt.isEmpty()) {
                    int[] time = getTime(timeFromEt);
                    timePickerDialog = new TimePickerDialog(CreateTaskActivity.this,
                            timeSetListener, time[0], time[1], true);
                } else {
                    timePickerDialog = new TimePickerDialog(CreateTaskActivity.this,
                            timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE), true);
                }
                timePickerDialog.show();
            }
        });

        binding.buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUrgent = !(binding.editTextDate.getText().toString().isEmpty() &&
                                    binding.editTextTime.getText().toString().isEmpty());

                if(binding.editTextTitle.getText().toString().isEmpty()) {
                    binding.editTextTitle.setError(getString(R.string.titleEmptyError));
                    binding.editTextTitle.requestFocus();
                    /*try {
                        binding.editTextTitle.getBackground().mutate()
                                .setTint(getResources().getColor(R.color.red));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                } else {
                    String date = binding.editTextDate.getText().toString();
                    String time = binding.editTextTime.getText().toString();

                    if(isUrgent) {
                        if(date.isEmpty()) {
                            date = getString(R.string.dateFormat,
                                    calendar.get(Calendar.DAY_OF_MONTH),
                                    calendar.get(Calendar.MONTH) +1,
                                    calendar.get(Calendar.YEAR));
                        }

                        if(time.isEmpty()) {
                            calendar.add(Calendar.HOUR_OF_DAY, 2);
                            time = getString(R.string.timeFormat,
                                    calendar.get(Calendar.HOUR_OF_DAY),
                                    calendar.get(Calendar.MINUTE));
                        }
                    }
                    hideKeyboard(v);

                    Task task = new Task();

                    task.setImportant(binding.checkBoxIsImportantTask.isChecked());
                    task.setDateAndTime(date, time);
                    task.setTitle(binding.editTextTitle.getText().toString());
                    task.setDescription(binding.editTextDescription.getText().toString());

                    Log.d("myLogs", "title: " + task.getTitle() + ", description: " +
                            task.getDescription() + ", important: " + task.isImportant() +
                            ", urgent = " + task.isUrgent());

                    try {
                        HelperFactory.getDatabaseHelper().getTaskDAO().create(task);
                        //Анимация в нужном фрагменте
                        setResult(RESULT_OK, new Intent()
                                .putExtra(Constants.RESULT_EXTRA_URGENT, task.isUrgent())
                                .putExtra(Constants.RESULT_EXTRA_IMPORTANT, task.isImportant()));
                        clearData();

                        if(isUrgent) {
                            //Создание уведомления
                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            Intent notifyIntent = new Intent(CreateTaskActivity.this, TaskReceiver.class);
                            notifyIntent.putExtra(Constants.EXTRA_ID, task.getId());
                            PendingIntent pendingIntent = PendingIntent
                                    .getBroadcast(CreateTaskActivity.this, 1, notifyIntent, 0);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, task.getUnixTime(), pendingIntent);
                        }
                        finish();
                    } catch (SQLException e) {
                        Log.e("myLogs", "error creating new task: " + e.toString());
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveData();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        readData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        saveData();
    }

    private void hideKeyboard(View v) {
        if(getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(v.getWindowToken(), 0, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.editTextDate.setText(getString(R.string.dateFormat, dayOfMonth, monthOfYear+1, year));
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            binding.editTextTime.setText(getString(R.string.timeFormat, hourOfDay, minute));
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
        }
    };

    private int[] getDate(String date) {
        int[] dateInt = new int[3];
        String[] dateString = date.split("\\.");
        for(int i = 0; i < dateString.length; i++) {
            dateInt[i] = Integer.parseInt(dateString[i]);
        }
        return dateInt;
    }

    private int[] getTime(String time) {
        int[] timeInt = new int[2];
        String[] timeString = time.split(":");
        for(int i = 0; i < timeString.length; i++) {
            timeInt[i] = Integer.parseInt(timeString[i]);
        }
        return timeInt;
    }

    private void saveData() {
        SharedPreferences sPref = getSharedPreferences("timeMgmtPref", MODE_PRIVATE);
        sPref.edit()
                .putString("title", binding.editTextTitle.getText().toString())
                .putString("description", binding.editTextDescription.getText().toString())
                .putString("date", binding.editTextDate.getText().toString())
                .putString("time", binding.editTextTime.getText().toString())
                .putBoolean("checked", binding.checkBoxIsImportantTask.isChecked())
                .apply();
    }

    private void clearData() {
        getPreferences(MODE_PRIVATE).edit().clear().apply();
    }

    private void readData() {
        SharedPreferences sPref = getSharedPreferences("timeMgmtPref", MODE_PRIVATE);
        binding.editTextTitle.setText(sPref.getString("title", ""));
        binding.editTextDescription.setText(sPref.getString("description", ""));
        binding.editTextDate.setText(sPref.getString("date", ""));
        binding.editTextTime.setText(sPref.getString("time", ""));
        binding.checkBoxIsImportantTask.setChecked(sPref.getBoolean("checked", false));
    }
}
