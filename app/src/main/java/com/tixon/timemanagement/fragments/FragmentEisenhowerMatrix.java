package com.tixon.timemanagement.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tixon.timemanagement.Constants;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.activities.CreateTaskActivity;
import com.tixon.timemanagement.databinding.FragmentEisenhowerBinding;

import java.lang.reflect.Field;

/**
 * Created by tikhon on 01.03.16
 */
public class FragmentEisenhowerMatrix extends Fragment {
    public static final int REQUEST_CODE_CREATE_TASK = 1;

    /*private static final String ARG_TASKS_ALL = "argument_tasks_all";
    private static final String ARG_TASKS_URGENT_AND_IMPORTANT = "argument_tasks_urgent_and_important";*/

    FragmentEisenhowerBinding binding;
    FragmentManager fm;
    FragmentTasksList fragmentUrgentAndImportant,
            fragmentUrgentAndNotImportant,
            fragmentNotUrgentAndImportant,
            fragmentNotUrgentAndNotImportant;

    public static FragmentEisenhowerMatrix newInstance() {
        FragmentEisenhowerMatrix fragment = new FragmentEisenhowerMatrix();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_eisenhower, container, false);
        fm = getChildFragmentManager();

        fragmentUrgentAndImportant = FragmentTasksList.newInstance(true, true, getString(R.string.urgentAndImportant));
        fragmentUrgentAndNotImportant = FragmentTasksList.newInstance(true, false, getString(R.string.urgentAndNotImportant));
        fragmentNotUrgentAndImportant = FragmentTasksList.newInstance(false, true, getString(R.string.notUrgentAndImportant));
        fragmentNotUrgentAndNotImportant = FragmentTasksList.newInstance(false, false, getString(R.string.notUrgentAndNotImportant));

        fm.beginTransaction().replace(R.id.urgentAndImportant, fragmentUrgentAndImportant).commit();
        fm.beginTransaction().replace(R.id.urgentAndNotImportant, fragmentUrgentAndNotImportant).commit();
        fm.beginTransaction().replace(R.id.notUrgentAndImportant, fragmentNotUrgentAndImportant).commit();
        fm.beginTransaction().replace(R.id.notUrgentAndNotImportant, fragmentNotUrgentAndNotImportant).commit();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent createTask = new Intent(getActivity(), CreateTaskActivity.class);
                startActivityForResult(createTask, REQUEST_CODE_CREATE_TASK);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CREATE_TASK:
                Log.d("myLogs", "activity result in fragment");
                if(resultCode == Activity.RESULT_OK) {
                    boolean urgent = data.getBooleanExtra(Constants.RESULT_EXTRA_URGENT, false);
                    boolean important = data.getBooleanExtra(Constants.RESULT_EXTRA_IMPORTANT, false);
                    showAnimation(urgent, important);
                }
                break;
            default: break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showAnimation(boolean urgent, boolean important) {
        if(urgent && important) {
            fragmentUrgentAndImportant.showTaskAdded();
        }
        if(urgent && !important) {
            fragmentUrgentAndNotImportant.showTaskAdded();
        }
        if(!urgent && important) {
            fragmentNotUrgentAndImportant.showTaskAdded();
        }
        if(!urgent && !important) {
            fragmentNotUrgentAndNotImportant.showTaskAdded();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
