package com.example.yummy.model.statistics;

import java.io.Serializable;
import java.util.List;

public class RestaurantStatistics implements Serializable {
    private int completedOrderAmount;
    private int withdrawnOrderAmount;

    private List<Double> incomePerDay;

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

    public List<Double> getIncomePerDay() {
        return incomePerDay;
    }

    public void setIncomePerDay(List<Double> incomePerDay) {
        this.incomePerDay = incomePerDay;
    }
}
