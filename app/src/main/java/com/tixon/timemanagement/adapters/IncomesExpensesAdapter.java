package com.tixon.timemanagement.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tixon.timemanagement.database.IncomesExpensesDAO;
import com.tixon.timemanagement.databinding.IncomesExpensesItemBinding;
import com.tixon.timemanagement.incomes_expenses.Money;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tikhon.osipov on 04.04.2016.
 */
public class IncomesExpensesAdapter extends RecyclerView.Adapter<IncomesExpensesAdapter.IncomesExpensesViewHolder> {

    private IncomesExpensesDAO dao;
    private ArrayList<Money> money;

    public IncomesExpensesAdapter(IncomesExpensesDAO dao) {
        money = new ArrayList<>();
        this.dao = dao;
        try {
            money.addAll(dao.getAllRecords());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IncomesExpensesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new IncomesExpensesViewHolder(IncomesExpensesItemBinding.inflate(inflater).getRoot());
    }

    @Override
    public void onBindViewHolder(IncomesExpensesViewHolder holder, int position) {
        holder.binding.setMoney(money.get(position));
    }

    @Override
    public int getItemCount() {
        return money.size();
    }

    public static class IncomesExpensesViewHolder extends RecyclerView.ViewHolder {

        IncomesExpensesItemBinding binding;

        public IncomesExpensesViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
