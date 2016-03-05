package com.tixon.timemanagement.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.activities.NavigationActivity;
import com.tixon.timemanagement.activities.TaskDescriptionActivity;
import com.tixon.timemanagement.task.Task;

import java.util.HashMap;

/**
 * Created by tikhon on 05.03.16
 */
public class NotificationUtils {

    private static Context ctx;
    private static NotificationUtils instance;

    private NotificationManager manager;
    private int lastId = 0;
    private HashMap<Integer, Notification> notifications;

    private NotificationUtils(Context context) {
        ctx = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifications = new HashMap<>();
    }

    public static NotificationUtils getInstance(Context context) {
        if(instance == null) {
            instance = new NotificationUtils(context);
        } else {
            ctx = context;
        }
        return instance;
    }

    public int createTaskNotification(Task task) {
        Intent notificationIntent = new Intent(ctx, TaskDescriptionActivity.class);
        notificationIntent.putExtra(Constants.EXTRA_ID, task.getId());

        Notification.Builder nb = new Notification.Builder(ctx)
                .setSmallIcon(R.drawable.ic_event_note_white_24dp)
                .setAutoCancel(true)
                .setTicker(task.getTitle())
                .setContentText(task.getDescription())
                //.setWhen(task.getUnixTime())
                .setWhen(System.currentTimeMillis())
                .setContentIntent(PendingIntent.getActivity(ctx, 0, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT))
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(ctx.getString(R.string.app_name));
        Notification notification = nb.build();

        manager.notify(lastId, notification);
        notifications.put(lastId, notification);
        return lastId++;
    }
}
