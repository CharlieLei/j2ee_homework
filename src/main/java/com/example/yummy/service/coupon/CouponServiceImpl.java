package com.example.yummy.service.coupon;

import com.example.yummy.dao.CouponDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.coupon.Coupon;

import java.util.List;

public class CouponServiceImpl implements CouponService {

    private CouponDao couponDao = DaoFactory.getCouponDao();

    @Override
    public boolean addCoupon(String restaurantId, Coupon coupon) {
        coupon.setRestaurantId(restaurantId);
        return couponDao.add(coupon);
    }

    @Override
    public List<Coupon> getCouponListByRestaurant(String restaurantId) {
        return couponDao.getCouponListByRestaurant(restaurantId);
    }

    @Override
    public List<Coupon> getCouponListByMember(String memberId) {
        return null;
    }
}
