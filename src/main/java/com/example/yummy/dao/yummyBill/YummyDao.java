package com.example.yummy.dao.yummyBill;

import com.example.yummy.model.yummyBill.YummyBill;

import java.sql.Timestamp;
import java.util.List;

public interface YummyDao {
    public boolean add(YummyBill yummyBill);

    public boolean modify(YummyBill yummyBill);

    public List<YummyBill> getAllUnsettledBills();

    public List<YummyBill> getAllSettledBills(Timestamp startTime, Timestamp endTime);
}
