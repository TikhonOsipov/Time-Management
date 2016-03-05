package com.tixon.timemanagement.fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tixon.timemanagement.R;
import com.tixon.timemanagement.databinding.FragmentIncomesExpensesBinding;

/**
 * Created by tikhon on 05.03.16
 */
public class FragmentIncomesExpenses extends Fragment {
    public static final int INCOMES = 1;
    public static final int EXPENSES = 2;
    public static final int INCOMES_AND_EXPENSES = 3;
    private static final String ARG_POSITION = "arg_position";
    FragmentIncomesExpensesBinding binding;
    int position;

    public static FragmentIncomesExpenses newInstance(int position) {
        FragmentIncomesExpenses fragment = new FragmentIncomesExpenses();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_incomes_expenses, container, false);
        Log.d("myLogs", "fragment viewPager position = " + position);
        return binding.getRoot();
    }
}
