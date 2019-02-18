package com.example.yummy.dao;

import com.example.yummy.model.yummyBill.YummyBill;

import java.util.List;

public interface YummyDao {
    public boolean add(YummyBill yummyBill);

    public boolean modify(YummyBill yummyBill);

    public List<YummyBill> getAllUnsettledBills();
}
