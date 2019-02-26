package com.example.yummy.service.order;

import com.example.yummy.dao.order.OrderDao;
import com.example.yummy.dao.restaurant.RestaurantDao;
import com.example.yummy.dao.yummyBill.YummyDao;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.yummyBill.YummyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YummyBillSettler {

    @Autowired
    private YummyDao yummyDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RestaurantDao restaurantDao;

    private static final double DISCOUNT = 0.9;

    @Scheduled(cron = "0 * * * * ?")
    public void settleBills() {
        System.out.println("********************settling bills************************");

        List<YummyBill> list = yummyDao.getAllUnsettledBills();

        for (YummyBill yummyBill: list) {
            Order order = orderDao.get(yummyBill.getOrderId());
            Restaurant restaurant = restaurantDao.get(order.getRestaurantId());

            double actualAmount = order.getTotalAmount() - order.getRefund();
            restaurant.setBalance(
                    restaurant.getBalance() + actualAmount * DISCOUNT
            );
            restaurantDao.modify(restaurant);

            yummyBill.setSettleAmount(actualAmount * (1 - DISCOUNT));
            yummyBill.setSettled(true);
            yummyDao.modify(yummyBill);
        }
    }
}
