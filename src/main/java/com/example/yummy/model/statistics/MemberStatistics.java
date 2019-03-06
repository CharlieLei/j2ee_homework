package com.example.yummy.model.statistics;

import com.example.yummy.model.order.Order;

import java.io.Serializable;
import java.util.List;

public class MemberStatistics implements Serializable {
    private int completedOrderAmount;
    private int withdrawnOrderAmount;

    private List<Double> expensePerDay;

    private List<Order> completedOrderList;
    private List<Order> withdrawnOrderList;

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

    public List<Order> getCompletedOrderList() {
        return completedOrderList;
    }

    public void setCompletedOrderList(List<Order> completedOrderList) {
        this.completedOrderList = completedOrderList;
    }

    public List<Order> getWithdrawnOrderList() {
        return withdrawnOrderList;
    }

    public void setWithdrawnOrderList(List<Order> withdrawnOrderList) {
        this.withdrawnOrderList = withdrawnOrderList;
    }
}
