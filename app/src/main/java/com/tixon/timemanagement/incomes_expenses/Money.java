package com.tixon.timemanagement.incomes_expenses;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;

/**
 * Created by tikhon.osipov on 04.04.2016.
 */
@DatabaseTable(tableName = "MoneyTurnover")
public class Money {
    public static final int INCOME = 1;
    public static final int EXPENSE = 2;
    public static final int ACCUMULATION = 3;

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private double amount;
    @DatabaseField
    private int status; //income, expense, etc.
    @DatabaseField
    private long time;

    private static Calendar calendar;

    public Money(String name, double amount, int status, long time) {
        Log.d("myLogs", "creating new instance of Money");
        this.name = name;
        this.amount = amount;
        this.status = status;
        this.time = time;
        if(calendar == null) {
            calendar = Calendar.getInstance();
            Log.d("myLogs", "instantiate calendar");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        calendar.setTimeInMillis(time);
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.DAY_OF_MONTH)).append(".");
        sb.append(calendar.get(Calendar.MONTH)).append(".");
        sb.append(calendar.get(Calendar.YEAR)).append(" ");
        sb.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":");
        sb.append(calendar.get(Calendar.MINUTE));
        return sb.toString();
    }

    public void setTime(long time) {
        this.time = time;
    }
}
