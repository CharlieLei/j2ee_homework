package com.example.yummy.model.statistics;

import java.io.Serializable;
import java.util.List;

public class MemberStatistics implements Serializable {
    private int completedOrderAmount;
    private int withdrawnOrderAmount;

    private List<Double> expensePerDay;

    public int getCompletedOrderAmount() {
        return completedOrderAmount;
    }

    public void setCompletedOrderAmount(int completedOrderAmount) {
        this.completedOrderAmount = completedOrderAmount;
    }

    public int getWithdrawnOrderAmount() {
        return withdrawnOrderAmount;
    }

    public void setWithdrawnOrderAmount(int withdrawnOrderAmount) {
        this.withdrawnOrderAmount = withdrawnOrderAmount;
    }

    public List<Double> getExpensePerDay() {
        return expensePerDay;
    }

    public void setExpensePerDay(List<Double> expensePerDay) {
        this.expensePerDay = expensePerDay;
    }
}
