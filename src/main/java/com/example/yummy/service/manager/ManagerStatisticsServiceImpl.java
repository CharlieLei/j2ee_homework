package com.example.yummy.service.manager;

import com.example.yummy.dao.member.MemberDao;
import com.example.yummy.dao.restaurant.RestaurantDao;
import com.example.yummy.dao.yummyBill.YummyDao;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantState;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.ManagerStatistics;
import com.example.yummy.model.yummyBill.YummyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ManagerStatisticsServiceImpl implements ManagerStatisticsService {

    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private YummyDao yummyDao;

    private static final long intervalMills = 24 * 60 * 60 * 1000;

    @Override
    public ManagerStatistics get(Timestamp startTime, Timestamp endTime) {
        ManagerStatistics statistics = new ManagerStatistics();

        List<RestaurantType> restaurantTypeList = new ArrayList<>(Arrays.asList(RestaurantType.values()));
        List<Integer> restaurantAmountList = new ArrayList<>();
        for (RestaurantType type: RestaurantType.values()) {
            List<Restaurant> restaurantList = restaurantDao.getRestaurantsByType(type, RestaurantState.ACTIVATED);
            restaurantAmountList.add(restaurantList.size());
        }
        statistics.setRestaurantTypeList(restaurantTypeList);
        statistics.setRestaurantAmountList(restaurantAmountList);

        List<Member> memberList = memberDao.getAll();
        statistics.setMemberAmount(memberList.size());

        List<Double> incomePerDay = new ArrayList<>();
        Timestamp currentTime = startTime;
        Timestamp newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        while (newDayTime.getTime() <= endTime.getTime()) {
            List<YummyBill> yummyBillList = yummyDao.getAllSettledBills(currentTime, newDayTime);

            double income = 0;
            for (YummyBill yummyBill: yummyBillList) {
                income += yummyBill.getSettleAmount();
            }
            incomePerDay.add(income);

            currentTime = new Timestamp(currentTime.getTime() + intervalMills);
            newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        }
        statistics.setIncomePerDay(incomePerDay);

        return statistics;
    }
}
