package com.example.yummy.model.statistics;

import com.example.yummy.model.restaurant.RestaurantType;

import java.io.Serializable;
import java.util.List;

public class ManagerStatistics implements Serializable {
    private List<RestaurantType> restaurantTypeList;
    private List<Integer> restaurantAmountList;

    private int memberAmount;

    private List<Double> incomePerDay;

    public List<RestaurantType> getRestaurantTypeList() {
        return restaurantTypeList;
    }

    public void setRestaurantTypeList(List<RestaurantType> restaurantTypeList) {
        this.restaurantTypeList = restaurantTypeList;
    }

    public List<Integer> getRestaurantAmountList() {
        return restaurantAmountList;
    }

    public void setRestaurantAmountList(List<Integer> restaurantAmountList) {
        this.restaurantAmountList = restaurantAmountList;
    }

    public int getMemberAmount() {
        return memberAmount;
    }

    public void setMemberAmount(int memberAmount) {
        this.memberAmount = memberAmount;
    }

    public List<Double> getIncomePerDay() {
        return incomePerDay;
    }

    public void setIncomePerDay(List<Double> incomePerDay) {
        this.incomePerDay = incomePerDay;
    }
}
