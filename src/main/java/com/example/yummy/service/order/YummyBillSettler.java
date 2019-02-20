package com.example.yummy.service.order;

import com.example.yummy.dao.OrderDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.YummyDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.yummyBill.YummyBill;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YummyBillSettler {

    private YummyDao yummyDao = DaoFactory.getYummyDao();
    private OrderDao orderDao = DaoFactory.getOrderDao();
    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    private static final double DISCOUNT = 0.9;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void settleBills() {
        List<YummyBill> list = yummyDao.getAllUnsettledBills();

        for (YummyBill yummyBill: list) {
            Order order = orderDao.get(yummyBill.getOrderId());
            Restaurant restaurant = restaurantDao.get(order.getRestaurantId());

            double actualAmount = order.getTotalAmount() - order.getRefund();
            restaurant.getRestaurantInfo().setBalance(
                    restaurant.getRestaurantInfo().getBalance() + actualAmount * DISCOUNT
            );
            restaurantDao.modify(restaurant);

            yummyBill.setSettled(true);
            yummyDao.modify(yummyBill);
        }
    }
}
