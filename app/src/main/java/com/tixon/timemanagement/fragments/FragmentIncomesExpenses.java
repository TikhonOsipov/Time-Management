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

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.adapters.IncomesExpensesAdapter;
import com.tixon.timemanagement.database.HelperFactory;
import com.tixon.timemanagement.database.IncomesExpensesDAO;
import com.tixon.timemanagement.databinding.FragmentIncomesExpensesBinding;
import com.tixon.timemanagement.incomes_expenses.Money;

import java.sql.SQLException;

/**
 * Created by tikhon on 05.03.16
 */
public class FragmentIncomesExpenses extends Fragment {
    public static final int INCOMES = 1;
    public static final int EXPENSES = 2;
    public static final int INCOMES_AND_EXPENSES = 3;
    private static final String ARG_POSITION = "arg_position";

    private IncomesExpensesDAO incomesExpensesDAO;
    private IncomesExpensesAdapter adapter;

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

        try {
            incomesExpensesDAO = HelperFactory.getDatabaseHelper().getIncomesExpensesDAO();
            adapter = new IncomesExpensesAdapter(incomesExpensesDAO);
            binding.recyclerViewIncomesExpenses.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recyclerViewIncomesExpenses.setAdapter(adapter);
            binding.totalIncomesExpenses.setText(String.valueOf(incomesExpensesDAO.getAvailableMoney()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }
}
