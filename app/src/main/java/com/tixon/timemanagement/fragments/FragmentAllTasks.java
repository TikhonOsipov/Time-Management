package com.tixon.timemanagement.fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tixon.timemanagement.R;
import com.tixon.timemanagement.adapters.TaskListActivityAdapter;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.FragmentAllTasksBinding;
import com.tixon.timemanagement.task.Task;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tikhon on 01.03.16
 */
public class FragmentAllTasks extends Fragment {

    FragmentAllTasksBinding binding;
    TaskListActivityAdapter adapter;
    TaskDAO taskDAO;
    /*ArrayList<Task> allTasks, urgentAndImportant, urgentAndNotImportant, notUrgentAndImportant, notUrgentAndNotImportant;
    ArrayList<Integer> sectionPositions;*/

    public FragmentAllTasks() {
        /*allTasks = new ArrayList<>();
        urgentAndImportant = new ArrayList<>();
        urgentAndNotImportant = new ArrayList<>();
        notUrgentAndImportant = new ArrayList<>();
        notUrgentAndNotImportant = new ArrayList<>();
        sectionPositions = new ArrayList<>();*/
    }

    public static FragmentAllTasks newInstance() {
        FragmentAllTasks fragment = new FragmentAllTasks();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_tasks, container, false);
        try {
            taskDAO = HelperFactory.getDatabaseHelper().getTaskDAO();
            /*urgentAndImportant.addAll(taskDAO.getTasksByUrgenceAndImportance(true, true));
            urgentAndNotImportant.addAll(taskDAO.getTasksByUrgenceAndImportance(true, false));
            notUrgentAndImportant.addAll(taskDAO.getTasksByUrgenceAndImportance(false, true));
            notUrgentAndNotImportant.addAll(taskDAO.getTasksByUrgenceAndImportance(false, false));*/
        } catch (SQLException e) {
            Log.e("myLogs", "error getting taskDAO in FragmentAllTasks: " + e.toString());
            e.printStackTrace();
        }

        /*allTasks.addAll(urgentAndImportant);
        allTasks.addAll(urgentAndNotImportant);
        allTasks.addAll(notUrgentAndImportant);
        allTasks.addAll(notUrgentAndNotImportant);*/

        /*if(!allTasks.isEmpty()) {
            sectionPositions.add(0);
            if(!urgentAndImportant.isEmpty()) {
                sectionPositions.add(urgentAndImportant.size());
            }
            if(!urgentAndNotImportant.isEmpty()) {
                sectionPositions.add(urgentAndNotImportant.size());
            }
            if(!notUrgentAndImportant.isEmpty()) {
                sectionPositions.add(notUrgentAndImportant.size());
            }
            if(!notUrgentAndNotImportant.isEmpty()) {
                sectionPositions.add(notUrgentAndNotImportant.size());
            }
        }*/

        adapter = new TaskListActivityAdapter(getActivity(), taskDAO);
        binding.taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.taskListRecyclerView.setAdapter(adapter);

        /*binding.taskListRecyclerView.setHasFixedSize(true);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9","10","11","12","13","14","15"});
        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, "section 1"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2, "section 2"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3, "section 3"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(7, "section 4"));

        SimpleSectionedRecyclerViewAdapter.Section[] sectionsArray = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mAdapter = new SimpleSectionedRecyclerViewAdapter(getActivity(), R.layout.category, R.id.section_text, simpleAdapter);
        mAdapter.setSections(sections.toArray(sectionsArray));
        binding.taskListRecyclerView.setAdapter(mAdapter);*/

        return binding.getRoot();
    }

    @SafeVarargs
    @SuppressWarnings("unused")
    private final void clearLists(ArrayList<Task>... lists) {
        if(lists.length != 0) {
            for(ArrayList<Task> list: lists) {
                try {
                    list.clear();
                } catch (Exception e) {
                    Log.e("myLogs", "FragmentAllTasks: clearLists(" + lists.length + "): error clearing list: " + e.toString());
                    e.printStackTrace();
                }
            }
        }
    }
}
