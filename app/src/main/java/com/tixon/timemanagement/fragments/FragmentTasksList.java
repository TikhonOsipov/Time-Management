package com.tixon.timemanagement.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tixon.timemanagement.R;
import com.tixon.timemanagement.activities.TaskListActivity;
import com.tixon.timemanagement.adapters.TasksAdapter;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.TaskDAO;
import com.tixon.timemanagement.databinding.FragmentTasksBinding;

import java.sql.SQLException;

/**
 * Created by tikhon on 27.02.16.
 */
public class FragmentTasksList extends Fragment {

    public static final String ARG_URGENT = "argument_urgent";
    public static final String ARG_IMPORTANT = "argument_important";
    public static final String ARG_BUNDLE = "fragment_arguments";
    public static final String ARG_NAME = "argument_name";

    //private static final String ARG_ALL_TASKS = "argument_all_tasks";
    //private static final String ARG_BACKGROUND = "argument_background";

    FragmentTasksBinding binding;
    TasksAdapter adapter;
    TaskDAO taskDAO;
    GestureDetector gestureDetector;
    Intent taskListActivityIntent;

    public FragmentTasksList() {}

    /*public static FragmentTasksList newInstance() {
        FragmentTasksList fragment = new FragmentTasksList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    public static FragmentTasksList newInstance(boolean urgent, boolean important, String name) {
        FragmentTasksList fragment = new FragmentTasksList();
        Bundle args = new Bundle();
        args.putBoolean(ARG_URGENT, urgent);
        args.putBoolean(ARG_IMPORTANT, important);
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false);
        gestureDetector = new GestureDetector(getActivity(), new SingleTapConfirm());

        binding.clickView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    //если был произведён click
                    Log.d("myLogs", "single tap");
                    taskListActivityIntent = new Intent(getActivity(), TaskListActivity.class);
                    taskListActivityIntent.putExtra(ARG_BUNDLE, getArguments());
                    try {
                        if (!taskDAO.getTasksByUrgenceAndImportance(
                                getArguments().getBoolean(ARG_URGENT),
                                getArguments().getBoolean(ARG_IMPORTANT)).isEmpty()) {
                            startActivity(taskListActivityIntent); //посмотреть список
                        }
                    } catch (SQLException e) {
                        Log.e("myLogs", "error querying taskDAO in FragmentTaskList: " + e.toString());
                        e.printStackTrace();
                    }
                    return false;
                } else {
                    //если листаем recyclerView
                    binding.taskRecyclerView.onTouchEvent(event);
                }
                return true;
            }
        });

        try {
            taskDAO = HelperFactory.getDatabaseHelper().getTaskDAO();
        } catch (SQLException e) {
            Log.e("myLogs", "error getting taskDAO in FragmentTaskList: " + e.toString());
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }

    @Override
    public void onResume() {
        adapter = new TasksAdapter(taskDAO,
                getArguments().getBoolean(ARG_IMPORTANT),
                getArguments().getBoolean(ARG_URGENT));

        binding.taskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.taskRecyclerView.setAdapter(adapter);
        super.onResume();
    }

    public void showTaskAdded() {
        Animation whiteFrameAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.white_background_show_alpha);
        whiteFrameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.whiteFrame.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.whiteFrame.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Animation imageTaskAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.image_task_hide_scale);
        imageTaskAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.imageTaskCreated.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.imageTaskCreated.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.whiteFrame.startAnimation(whiteFrameAnimation);
        binding.imageTaskCreated.startAnimation(imageTaskAnimation);
    }
}
