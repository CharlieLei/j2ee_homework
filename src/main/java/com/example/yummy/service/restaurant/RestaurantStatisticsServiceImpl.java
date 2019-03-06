package com.example.yummy.service.restaurant;

import com.example.yummy.dao.member.MemberDao;
import com.example.yummy.dao.order.OrderDao;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.statistics.RestaurantStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantStatisticsServiceImpl implements RestaurantStatisticsService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;

    private static final long intervalMills = 24 * 60 * 60 * 1000;

    @Override
    public RestaurantStatistics get(String restaurantId,
                                    Timestamp startTime, Timestamp endTime,
                                    double lowerAmount, double upperAmount,
                                    MemberLevel memberLevel) {

        RestaurantStatistics statistics = new RestaurantStatistics();

        List<Order> completedOrderList = orderDao.getAllOrdersOfThisRestaurant(
                restaurantId,
                startTime, endTime,
                lowerAmount, upperAmount,
                OrderState.COMPLETED
        );
        statistics.setCompletedOrderList(new ArrayList<>());
        int completedOrderAmount = 0;
        for (Order order: completedOrderList) {
            Member member = memberDao.get(order.getMemberId());

            if (memberLevel == null || member.getMemberInfo().getLevel() == memberLevel) {
                completedOrderAmount++;
                statistics.getCompletedOrderList().add(order);
            }
        }
        statistics.setCompletedOrderAmount(completedOrderAmount);

        List<Order> withdrawnOrderList = orderDao.getAllOrdersOfThisRestaurant(
                restaurantId,
                startTime, endTime,
                lowerAmount, upperAmount,
                OrderState.CANCELLED
        );
        statistics.setWithdrawnOrderList(new ArrayList<>());
        int withdrawnOrderAmount = 0;
        for (Order order: withdrawnOrderList) {
            Member member = memberDao.get(order.getMemberId());

            if (memberLevel == null || member.getMemberInfo().getLevel() == memberLevel) {
                withdrawnOrderAmount++;
                statistics.getWithdrawnOrderList().add(order);
            }
        }
        statistics.setWithdrawnOrderAmount(withdrawnOrderAmount);

        List<Double> incomePerDay = new ArrayList<>();
        Timestamp currentTime = startTime;
        Timestamp newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        while (newDayTime.getTime() <= endTime.getTime()) {
            List<Order> orderList = orderDao.getAllOrdersOfThisRestaurant(
                    restaurantId,
                    currentTime, newDayTime,
                    lowerAmount, upperAmount,
                    null
            );

            double income = 0;
            for (Order order: orderList) {
                Member member = memberDao.get(order.getMemberId());

                if (memberLevel == null || member.getMemberInfo().getLevel() == memberLevel) {
                    income += (order.getTotalAmount() - order.getRefund());
                }
            }
            incomePerDay.add(income);

            currentTime = new Timestamp(currentTime.getTime() + intervalMills);
            newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        }
        statistics.setIncomePerDay(incomePerDay);

        return statistics;
    }
}
