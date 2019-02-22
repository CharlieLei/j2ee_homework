package com.example.yummy.model.yummyBill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "yummyBills")
public class YummyBill implements Serializable {
    @Id
    @Column(name = "tradingDate")
    private Timestamp tradingDate;
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "settleAmount")
    private double settleAmount;
    @Column(name = "isSettled")
    private boolean isSettled;

    public YummyBill() {
    }

    public Timestamp getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Timestamp tradingDate) {
        this.tradingDate = tradingDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }
}
