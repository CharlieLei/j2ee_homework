package com.example.yummy.service.order;

import com.example.yummy.model.Address;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.order.OrderVO;

import java.util.List;

public interface OrderService {

    public OrderVO get(int orderId);

    public boolean place(String memberId, String restaurantId,
                         Address senderAddr, Address receiverAddr,
                         OrderItem[] productItemList, double totalAmount);

    public boolean cancel(int orderId);

    public boolean complete(int orderId);

    public List<OrderVO> getAllOrders(String memberId, OrderState orderState);

    public List<OrderVO> getAllOrdersOfThisRestaurant(String restaurantId, OrderState orderState);
}
