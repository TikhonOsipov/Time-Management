package com.tixon.timemanagement.adapters;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.activities.TaskDescriptionActivity;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.TaskListItemBinding;
import com.tixon.timemanagement.listeners.OnMultiSelectionListener;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tikhon on 28.02.16
 */
public class TaskListActivityAdapter extends
        RecyclerView.Adapter<TaskListActivityAdapter.TaskViewHolder> {

    private boolean isInMultiSelection; //отвечает за режим MultiSelection
    private boolean urgent;
    private boolean important;
    private TaskDAO taskDAO;
    private Activity activity;
    private String at = " ";

    private OnMultiSelectionListener onMultiSelectionListener;

    public void setOnMultiSelectionListener(OnMultiSelectionListener listener) {
        this.onMultiSelectionListener = listener;
    }

    ArrayList<Task> taskList;
    ArrayList<Long> selectedPositions; //позиции выбранных элементов в списке

    public TaskListActivityAdapter(Activity activity, TaskDAO taskDAO, boolean urgent, boolean important) {
        this();
        this.activity = activity;
        this.taskDAO = taskDAO;
        this.urgent = urgent;
        this.important = important;
        try {
            taskList.addAll(taskDAO.getTasksByUrgenceAndImportance(urgent, important));
            if(taskList.isEmpty()) {
                activity.finish();
            }
        } catch (SQLException e) {
            Log.e("myLogs", "error getting urgent and important tasks in taskListActivityAdapter: " + e.toString());
            e.printStackTrace();
        }
    }

    public TaskListActivityAdapter(Activity activity, TaskDAO taskDAO) {
        this();
        this.activity = activity;
        this.taskDAO = taskDAO;
        try {
            taskList.addAll(taskDAO.getAllTasks());
        } catch (SQLException e) {
            Log.e("myLogs", "error getting all tasks in taskListActivityAdapter: " + e.toString());
            e.printStackTrace();
        }
    }

    private TaskListActivityAdapter() {
        selectedPositions = new ArrayList<>();
        taskList = new ArrayList<>();
        isInMultiSelection = false;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //TaskViewHolder holder = new TaskViewHolder(TaskListItemBinding.inflate(inflater, parent, false).getRoot());
        at = parent.getContext().getString(R.string.dateAtTime);
        return new TaskViewHolder(TaskListItemBinding.inflate(inflater, parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.binding.setTask(taskList.get(position));
        holder.position = (long) position;
        holder.binding.taskListItemDateTime.setText(getDate(taskList.get(position)));
        holder.binding.selection.setVisibility(selectedPositions.contains((long) position) ?
                View.VISIBLE : View.GONE); //выделить

        Log.d("myLogs", "position = " + position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    private String getDate(Task task) {
        StringBuilder result = new StringBuilder();
        String date = task.getDate();
        String time = task.getTime();
        result.append(date);
        if(result.toString().isEmpty()) {
            result.append(time);
        } else {
            if(!time.isEmpty()) {
                result.append(" ");
                result.append(at);
                result.append(" ");
                result.append(time);
            }
        }
        return result.toString();
    }

    /**
     * удалить выбранные элементы в режиме MultiSelect
     */
    public void deleteSelectedItems() {
        for(long position: selectedPositions) {
            int pos = (int) position;
            try {
                DeleteBuilder<Task, Integer> deleteBuilder = taskDAO.deleteBuilder();
                deleteBuilder.where().eq("id", taskList.get(pos).getId());
                deleteBuilder.delete();
            } catch (SQLException e) {
                Log.e("myLogs", "error deleting task by id: " + e.toString());
                e.printStackTrace();
            }
        }
        selectedPositions.clear();
        taskList.clear();
        try {
            taskList.addAll(taskDAO.getTasksByUrgenceAndImportance(urgent, important));
            notifyDataSetChanged();
            onMultiSelectionListener.onSelectionEnd(taskList);
            isInMultiSelection = false;
        } catch (SQLException e) {
            Log.e("myLogs", "error getting tasks in taskListActivityAdapter: " + e.toString());
            e.printStackTrace();
        }
    }

    public void unCheckAll() {
        for(long position: selectedPositions) {
            notifyItemChanged((int) position);
        }
        selectedPositions.clear();
        isInMultiSelection = false;
        onMultiSelectionListener.onSelectionEnd(taskList);
    }

    /**
     * ViewHolder class
     */
    public class TaskViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TaskListItemBinding binding;
        long position;

        public TaskViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            position = 0;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(isInMultiSelection) {
                //если позиция не выбрана
                if(!selectedPositions.contains(position)) {
                    //Log.d("myLogs", "added position to multiSelection: " + position);
                    selectedPositions.add(position); //добавить её в selectedPositions
                    binding.selection.setVisibility(View.VISIBLE); //выделить
                    onMultiSelectionListener.onSelection(selectedPositions.size());
                }
                //если позиция уже есть в этом списке
                else {
                    //Log.d("myLogs", "removed position from multiSelection: " + position);
                    selectedPositions.remove(position); //удалить её из этого списка
                    binding.selection.setVisibility(View.GONE); //снять выделение
                    onMultiSelectionListener.onSelection(selectedPositions.size());
                    //если список оказался пустым
                    if(selectedPositions.isEmpty()) {
                        isInMultiSelection = false; //выйти из режима multiSelection
                        //уведомить об окончании выделения
                        onMultiSelectionListener.onSelectionEnd(taskList);
                        //Log.d("myLogs", "exit multiSelection mode");
                    }
                }
            } else {
                Log.d("myLogs", "clicked element position: " + position);
                //обработка onClick
                int pos = (int) position;
                int id = taskList.get(pos).getId();
                Intent viewTask = new Intent(activity, TaskDescriptionActivity.class);
                viewTask.putExtra(Constants.EXTRA_ID, id);
                activity.startActivity(viewTask);
            }
            //notifyDataSetChanged();
        }

        @Override
        public boolean onLongClick(View v) {
            if(onMultiSelectionListener != null) {
                if(!isInMultiSelection) {
                    isInMultiSelection = true;
                    binding.selection.setVisibility(View.VISIBLE); //выделить первоначальный элемент
                    //Log.d("myLogs", "multiSelection started");
                    onMultiSelectionListener.onSelectionStart(); //уведомить о начале выделения
                    //notifyDataSetChanged();
                }
            }
            return false;
        }
    }
}
