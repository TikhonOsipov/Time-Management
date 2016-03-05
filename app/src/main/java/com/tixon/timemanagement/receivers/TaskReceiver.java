package com.tixon.timemanagement.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.task.Task;
import com.tixon.timemanagement.utils.NotificationUtils;

import java.sql.SQLException;

/**
 * Created by tikhon on 05.03.16
 */
public class TaskReceiver extends BroadcastReceiver {
    TaskDAO taskDAO;
    Task task = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            taskDAO = HelperFactory.getDatabaseHelper().getTaskDAO();
            task = taskDAO.queryForId(intent.getIntExtra(Constants.EXTRA_ID, 0));
            if(task != null) {
                NotificationUtils.getInstance(context).createTaskNotification(task);
            }
        } catch (SQLException e) {
            Log.e("myLogs", "error reading task in TaskReceiver: " + e.toString());
            e.printStackTrace();
        }
    }
}
