package com.tixon.timemanagement.listeners;

import com.tixon.timemanagement.task.Task;

import java.util.ArrayList;

/**
 * Created by tikhon on 29.02.16.
 */
public interface OnMultiSelectionListener {
    void onSelectionStart();
    void onSelectionEnd(ArrayList<Task> taskList);
    void onSelection(int count);
}
