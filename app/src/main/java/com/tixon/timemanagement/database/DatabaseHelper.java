package com.tixon.timemanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tixon.timemanagement.incomes_expenses.Money;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;

/**
 * Created by tikhon on 27.02.16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String LOG_TAG = "logsDatabase";

    private static final String DATABASE_NAME = "time_management_database.db";

    private static final int DATABASE_VERSION = 1;

    private TaskDAO taskDAO = null;
    private IncomesExpensesDAO incomesExpensesDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Task.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "error creating database: " + e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public TaskDAO getTaskDAO() throws SQLException{
        if(taskDAO == null) {
            taskDAO = new TaskDAO(getConnectionSource(), Task.class);
        }
        return taskDAO;
    }

    public IncomesExpensesDAO getIncomesExpensesDAO() throws SQLException{
        if(incomesExpensesDAO == null) {
            incomesExpensesDAO = new IncomesExpensesDAO(getConnectionSource(), Money.class);
        }
        return incomesExpensesDAO;
    }

    @Override
    public void close() {
        super.close();
        taskDAO = null;
    }
}
