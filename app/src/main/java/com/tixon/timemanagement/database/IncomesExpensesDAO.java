package com.tixon.timemanagement.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.tixon.timemanagement.incomes_expenses.Money;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tikhon.osipov on 04.04.2016.
 */
public class IncomesExpensesDAO extends BaseDaoImpl<Money, Integer> {


    protected IncomesExpensesDAO(ConnectionSource connectionSource, Class<Money> dataClass)
            throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Money> getAllRecords() throws SQLException {
        return this.queryForAll();
    }

    public double getAvailableMoney() throws SQLException {
        QueryBuilder<Money, Integer> incomesQueryBuilder = queryBuilder();
        incomesQueryBuilder.where().eq("status", Money.INCOME);
        QueryBuilder<Money, Integer> expensesQueryBuilder = queryBuilder();
        expensesQueryBuilder.where().eq("status", Money.EXPENSE);
        QueryBuilder<Money, Integer> accumulationQueryBuilder = queryBuilder();
        accumulationQueryBuilder.where().eq("status", Money.ACCUMULATION);
        ArrayList<Money> incomes = new ArrayList<>(query(incomesQueryBuilder.prepare()));
        ArrayList<Money> expenses = new ArrayList<>(query(expensesQueryBuilder.prepare()));
        expenses.addAll(query(accumulationQueryBuilder.prepare()));
        return sum(incomes) - sum(expenses);
    }

    private double sum(ArrayList<Money> list) {
        double s = 0;
        for(Money m: list) {
            s += m.getAmount();
        }
        return s;
    }
 }
