package com.tixon.timemanagement.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tikhon on 27.02.16.
 */
public class TaskDAO extends BaseDaoImpl<Task, Integer> {

    protected TaskDAO(ConnectionSource connectionSource,
                      Class<Task> dataClass) throws SQLException{
        super(connectionSource, dataClass);
    }

    public List<Task> getAllTasks() throws SQLException{
        return this.queryForAll();
    }

    public List<Task> getTasksByUrgenceAndImportance(boolean urgent, boolean important) throws SQLException{
        QueryBuilder<Task, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq("urgent", urgent).and().eq("important", important);
        PreparedQuery<Task> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }
}
