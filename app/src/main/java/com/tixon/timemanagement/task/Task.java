package com.tixon.timemanagement.task;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by tikhon on 26.02.16
 */
@DatabaseTable(tableName = "task")
public class Task {

    public Task() {
        //в стандартном конструкторе задать дефолтные параметры
        this.important = false;
        this.urgent = false;
        this.unixTime = 0;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private long unixTime;

    @DatabaseField
    private String date;

    @DatabaseField
    private String time;

    @DatabaseField
    private boolean urgent;

    @DatabaseField
    private boolean important;

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if(!date.isEmpty()) {
            this.urgent = true;
        }
    }

    /**
     *
     * @return дату в виде числового массива
     */
    public int[] getDateArray() {
        String[] date = this.date.split("\\.");
        int[] array = new int[date.length];
        for(int i = 0; i < date.length; i++) {
            array[i] = Integer.parseInt(date[i]);
        }
        return array;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        if(!time.isEmpty()) {
            this.urgent = true;
        }
    }

    public void setDateAndTime(String date, String time) {
        this.date = date;
        this.time = time;
        this.urgent = !(date.isEmpty() && time.isEmpty());
    }

    /**
     *
     * @return время в виде числового массива
     */
    public int[] getTimeArray() {
        String[] time = this.time.split(":");
        int[] array = new int[time.length];
        for (int i = 0; i < time.length; i++) {
            array[i] = Integer.parseInt(time[i]);
        }
        return array;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUrgent() {
        return this.urgent;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
        if(unixTime != 0) {
            this.urgent = true;
        }
    }
}
