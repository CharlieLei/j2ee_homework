package com.example.yummy.service.pay;

public interface PayService {
    public boolean isLoginInfoCorrect(String accountId, String password);

    public boolean payOrder(String memberAccountId, int orderId);

    public boolean withdrawOrder(int orderId);
}
