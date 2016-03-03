package com.tixon.timemanagement.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.TaskItemBinding;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tikhon on 27.02.16.
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    ArrayList<Task> taskList;

    public TasksAdapter(TaskDAO dao, boolean important, boolean urgent) {
        taskList = new ArrayList<>();
        try {
            taskList.addAll(dao.getTasksByUrgenceAndImportance(urgent, important));
        } catch (SQLException e) {
            Log.e("myLogs", "error getting tasks in adapter: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TaskItemBinding binding = TaskItemBinding.inflate(inflater, parent, false);
        return new TaskViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.binding.setTask(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TaskItemBinding binding;

        public TaskViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
