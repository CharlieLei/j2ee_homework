package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.coupon.Coupon;
import com.example.yummy.service.coupon.CouponService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/coupon")
public class CouponController {

    private CouponService couponService = ServiceFactory.getCouponServiceImpl();

    @RequestMapping(value = "/add")
    public boolean addCoupon(@RequestParam(name = "restaurantId") String restaurantId,
                             @RequestParam(name = "coupon") String couponJson) {

        Gson gson = new GsonBuilder().create();
        Coupon coupon = gson.fromJson(couponJson, Coupon.class);
        return couponService.addCoupon(restaurantId, coupon);
    }

    @RequestMapping(value = "/getCouponListByRestaurant")
    public List<Coupon> getCouponListByRestaurant(@RequestParam(name = "restaurantId") String restaurantId) {
        return couponService.getCouponListByRestaurant(restaurantId);
    }

    @RequestMapping(value = "/getCouponListByMember")
    public List<Coupon> getCouponListByMember(@RequestParam(name = "memberId") String memberId) {
        return couponService.getCouponListByMember(memberId);
    }
}
